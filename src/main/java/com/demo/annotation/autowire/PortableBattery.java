package com.demo.annotation.autowire;

import org.springframework.stereotype.Component;

@Component("portableBattery")
public class PortableBattery implements USB {
    public void info(){
        System.out.println("PortableBattery usb");
    }
}
