package com.demo.aop.demo2;

import org.springframework.stereotype.Component;

@Component("landlord2")
public class Landlord2 {

    // 仅仅只是实现了核心的业务功能
    public void service() {
        System.out.println("签合同2");
        System.out.println("收房租2");
    }


    // 仅仅只是实现了核心的业务功能
    public void service2() {
        System.out.println("签合同2");
        System.out.println("收房租2");
    }
}