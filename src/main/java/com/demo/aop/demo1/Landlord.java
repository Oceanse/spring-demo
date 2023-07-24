package com.demo.aop.demo1;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("landlord")
public class Landlord {



    /**
     * 核心的业务功能
     */
    public void service() {
        System.out.println("签合同");
        System.out.println("收房租");
    }



    /**
     * 核心的业务功能
     */
    public void service2() {
        System.out.println("签合同2");
        System.out.println("收房租2");
    }



    /**
     * 核心的业务功能
     */
    public void service3() {
        Optional<Object> empty = Optional.empty();
        empty.get();
    }
}