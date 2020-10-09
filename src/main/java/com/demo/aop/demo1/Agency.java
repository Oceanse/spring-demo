package com.demo.aop.demo1;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Agency {

    @Before("execution(* com.demo.aop.demo1.Landlord.service())")
    public void before(){
        System.out.println("带租客看房");
        System.out.println("谈价格");
    }

    @After("execution(* com.demo.aop.demo1.Landlord.service())")
    public void after(){
        System.out.println("交钥匙");
    }
}