package com.demo.aop.demo2;

import com.demo.aop.demo1.Landlord;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Landlord2Test {
    //测试注解开发aop
    @Test
    public void test(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop/springaop.xml");
        Landlord2 landlord2= context.getBean("landlord2", Landlord2.class);
        landlord2.service();
    }
}