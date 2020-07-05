package com.briup.ssm.service;

import com.briup.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    void save(SysLog sysLog)throws Exception;
    List<SysLog> findAll() throws Exception;
}
