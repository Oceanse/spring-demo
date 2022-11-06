package com.demo.aop.demo1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面类/增强类
 * 切入点表达式：知道对哪个类里面的哪个方法进行增强
 * 语法结构： execution([权限修饰符] [返回类型] [类全路径] [方法名称]([参数列表]) )
 * 例 1：对 com.atguigu.dao.BookDao 类里面的 add 进行增强
 * 		execution(* com.atguigu.dao.BookDao.add(..))
 * 例 2：对 com.atguigu.dao.BookDao 类里面的所有的方法进行增强
 * 		execution(* com.atguigu.dao.BookDao.* (..))
 * 例 3：对 com.atguigu.dao 包里面所有类，类里面所有方法进行增强
 * 		execution(* com.atguigu.dao.*.* (..))
 */
@Component
@Aspect
public class Agency {

    /**
     * @Before前置通知，在连接点方法前调用
     */
    @Before("execution(* com.demo.aop.demo1.Landlord.service())")//切入点：实际被真正增强的方法称为切入点
    public void before(){
        //实际增强的逻辑部分称为通知
        System.out.println("带租客看房");
        System.out.println("谈价格");
    }

    /**
     *  @After后置通知，在连接点方法后调用
     */
    @After("execution(* com.demo.aop.demo1.Landlord.service())")
    public void after(){
        System.out.println("交钥匙");
    }


    /**
     * @Around ：同时完成前置和后置通知
     */
    @Around("execution(* com.demo.aop.demo1.Landlord.service2())")
    public void around(ProceedingJoinPoint joinPoint) {
        System.out.println("带租客看房2");
        System.out.println("谈价格2");

        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("交钥匙2");
    }

    /**
     * 异常通知，当连接点方法异常时调用
     */
    @AfterThrowing("execution(* com.demo.aop.demo1.Landlord.service3())")
    public void afterThrowing() {
        System.out.println("afterThrowing.........");
    }


}