package com.zys.springboot.annotationdemo.dao;

import com.zys.springboot.annotationdemo.entity.SystemLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemLogDao {
    int createLog(SystemLog log);
}
