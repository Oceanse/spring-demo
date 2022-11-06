package com.demo.aop.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class AOPTest {


    @Test
    public void testBeforeAndAfter(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop/springaop.xml");
        Landlord landlord = context.getBean("landlord", Landlord.class);
        landlord.service();
    }


    @Test
    public void testAround(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop/springaop.xml");
        Landlord landlord = context.getBean("landlord", Landlord.class);
        landlord.service2();
    }


    @Test
    public void testAfterThrowing(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop/springaop.xml");
        Landlord landlord = context.getBean("landlord", Landlord.class);
        landlord.service3();
    }
}
