package com.demo.aop.demo3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Agency3 {

    //使用 @Around 注解来同时完成前置和后置通知
    @Around("execution(* com.demo.aop.demo3.Landlord3.service())")
    public void around(ProceedingJoinPoint joinPoint) {
        System.out.println("带租客看房");
        System.out.println("谈价格");

        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("交钥匙");
    }
}