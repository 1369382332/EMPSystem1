package com.briup.ssm.controller;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.ssm.domain.SysLog;
import com.briup.ssm.service.ISysLogService;

/*
 * 获取日志所有数据
 * 	  visitTime 访问时间		前置通知可以确定
	  username	当前用户		前置通知
	  method	访问的方法	前置通知
	  ip		客户端ip地址
	  url		访问资源路径
	  executionTime	执行时长
 */
@Component
@Aspect
public class LogAop {

	//需要web.xml文件中配置 监听器RequestContextListener
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ISysLogService sysLogService;
	
	private Date startTime;	//访问时间
	private Class executionClass;	//访问类
	private Method executionMethod;	//访问方法
	private String username;	//当前用户
	
	//获取访问时间 访问方法 当前用户信息
	@Before("execution(* com.briup.ssm.controller.*.*(..))")
	public void doBefore(JoinPoint jp) throws NoSuchMethodException, SecurityException {
		System.out.println("Before执行");
		//1.获取访问时间
		startTime = new Date();
		
		//2.获取访问类
		executionClass = jp.getTarget().getClass();
		//3.获取访问方法
		String methodName = jp.getSignature().getName();
		//获取方法参数
		Object[] args = jp.getArgs();
		if(args == null || args.length == 0) {
			//无参
			executionMethod = executionClass.getMethod(methodName);
		}else {
			//有参
			Class[] classArgs = new Class[args.length];
			for(int i = 0; i < args.length; i++) {
				classArgs[i] = args[i].getClass();
			}
			executionMethod = executionClass.getDeclaredMethod(methodName, classArgs);
		}
		
		//4.获取当前用户
		SecurityContext context = SecurityContextHolder.getContext();
		User user = (User) context.getAuthentication().getPrincipal();
		username = user.getUsername();
	}
	
	//获取日志中其他信息，运行时长、ip地址、url
	@After("execution(* com.briup.ssm.controller.*.*(..))")
	public void doAfter(JoinPoint jp) throws Exception {
		//1.获取执行时长
		long executionTime = new Date().getTime() - startTime.getTime();
		
		//2.获取ip地址
		String ip = request.getRemoteAddr();
		
		//3.获取url
		String url = null;
		if(executionClass != null && executionMethod != null && 
				executionClass != LogAop.class && executionClass!=SysLogController.class) {
			//获取类上的 @RequestMapping("/role")注解对象
			RequestMapping classAnnotation = 
					(RequestMapping)executionClass.getAnnotation(RequestMapping.class);
			if(classAnnotation != null) {
				//获取方法上 @RequestMapping("/findAll")注解对象
				RequestMapping methodAnnotation = 
						executionMethod.getAnnotation(RequestMapping.class);

				if(methodAnnotation != null) {
					url = classAnnotation.value()[0] + methodAnnotation.value()[0];
				}
			}
		}
		
		if(url != null) {
			//4.准备SysLog对象
			SysLog sysLog = new SysLog();
			sysLog.setVisitTime(startTime);
			sysLog.setExecutionTime(executionTime);
			sysLog.setUsername(username);
			sysLog.setIp(ip);
			sysLog.setUrl(url);
			String method = "[类名]" + executionClass.getName() + " [方法名]" + executionMethod.getName();
			sysLog.setMethod(method);
			
			//5.调用service代码，将数据插入数据库
			sysLogService.save(sysLog);
		}
	}
}
