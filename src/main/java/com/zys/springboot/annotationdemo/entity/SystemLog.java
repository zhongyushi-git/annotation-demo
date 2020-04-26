package com.zys.springboot.annotationdemo.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SystemLog {
    private String id;
    private String title;
    private String describe;
    private Date create_time;
    private String method;
    private String error;
}
