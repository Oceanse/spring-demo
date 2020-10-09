package com.demo.aop.demo3;

import com.demo.aop.demo2.Landlord2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class Landlord3Test {

    //测试注解开发aop
    @Test
    public void test(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop/springaop.xml");
        Landlord3 landlord3= context.getBean("landlord3", Landlord3.class);
        landlord3.service();
    }
}