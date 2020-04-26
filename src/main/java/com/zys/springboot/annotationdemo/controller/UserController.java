package com.zys.springboot.annotationdemo.controller;

import com.zys.springboot.annotationdemo.config.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    //使用日志注解
    @Log(title = "用户模块",describe = "获取用户列表")
    @GetMapping("/get")
    public String get(){
        return "Hello word!";
    }

    @Log(title = "用户模块",describe = "测试接口")
    @GetMapping("/test")
    public String test(){
        return "Hello Test!";
    }

}