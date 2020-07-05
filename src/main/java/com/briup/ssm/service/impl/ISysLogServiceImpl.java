package com.briup.ssm.service.impl;

import com.briup.ssm.dao.ISysLogDao;
import com.briup.ssm.domain.SysLog;
import com.briup.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ISysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog)throws Exception {
        sysLogDao.save(sysLog);
    }
    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
