package com.demo.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@Component //如果不设置id，直接使用@Component来创建bean,Spring会默认的为它生成ID,即将类名首字母变为小写。
@Scope("singleton") //scope默认为singleton
public class ChangHong {
    public void show(){
        System.out.println("It is ChangHong TV");
    }
}

