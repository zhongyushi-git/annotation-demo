package com.zys.springboot.annotationdemo.service;

import com.zys.springboot.annotationdemo.entity.SystemLog;

public interface SystemLogService {
    int createLog(SystemLog log);
}
