package com.demo.annotation.autowire;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 *  @Autowired+ @Qualifier常用在自动装配接口实现类对象场景
 */
@Component
public class People {

    @Autowired
    @Qualifier("pc")
    USB usb;

    public void showCellphone(){
        usb.info();
    }


}
