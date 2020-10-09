package com.demo.annotation.autowire;

import org.springframework.stereotype.Component;

@Component("usb")
public interface USB {
    public void info();
}
