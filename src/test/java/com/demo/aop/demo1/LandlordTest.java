package com.demo.aop.demo1;

import com.demo.aop.demo1.Landlord;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class LandlordTest {
    //测试注解开发aop
    @Test
    public void test(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop/springaop.xml");
        Landlord landlord = context.getBean("landlord", Landlord.class);
        landlord.service();
    }
}