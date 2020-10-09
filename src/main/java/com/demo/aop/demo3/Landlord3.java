package com.demo.aop.demo3;

import org.springframework.stereotype.Component;

@Component("landlord3")
public class Landlord3 {

    // 仅仅只是实现了核心的业务功能
    public void service() {
        System.out.println("签合同2");
        System.out.println("收房租2");
    }
}