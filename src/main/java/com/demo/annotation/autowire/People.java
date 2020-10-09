package com.demo.annotation.autowire;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class People {

    @Autowired
    @Qualifier("pc")
    USB usb;

    public void showCellphone(){
        usb.info();
    }


}
