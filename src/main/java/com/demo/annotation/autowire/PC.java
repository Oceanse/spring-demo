package com.demo.annotation.autowire;

import org.springframework.stereotype.Component;

@Component("pc")
public class PC implements USB {
    public void info(){
        System.out.println("PC usb");
    }
}
