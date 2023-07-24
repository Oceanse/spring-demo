package com.demo.aop.demo2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;

@Component
@Aspect
public class Agency2 {

    /**
     * 相同切入点抽取
     */
    @Pointcut("execution(* com.demo.aop.demo2.Landlord2.service())")
    public void lService() {
    }


    @Pointcut("execution(* com.demo.aop.demo2.Landlord2.service2())")
    public void lService2() {
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


    @Around("lService2()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("带租客看房2");
        System.out.println("谈价格2");
        joinPoint.proceed();
        System.out.println("交钥匙2");
    }
}