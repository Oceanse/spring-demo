package com.demo.aop.demo2;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Agency2 {

    /**
     * 相同切入点抽取
     */
    @Pointcut("execution(* com.demo.aop.demo2.Landlord2.service())")
    public void lService() {
    }

    @Before("lService()")
    public void before() {
        System.out.println("带租客看房2");
        System.out.println("谈价格2");
    }

    @After("lService()")
    public void after() {
        System.out.println("交钥匙2");
    }
}