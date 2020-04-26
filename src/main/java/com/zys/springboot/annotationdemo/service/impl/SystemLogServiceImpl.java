package com.zys.springboot.annotationdemo.service.impl;

import com.zys.springboot.annotationdemo.dao.SystemLogDao;
import com.zys.springboot.annotationdemo.entity.SystemLog;
import com.zys.springboot.annotationdemo.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLogServiceImpl implements SystemLogService {
    @Autowired
    private SystemLogDao systemLogDao;
    @Override
    public int createLog(SystemLog log) {
        return systemLogDao.createLog(log);
    }
}
