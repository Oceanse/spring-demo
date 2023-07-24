package com.demo.aop.demo2;

import com.demo.aop.demo1.Landlord;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class AOPTest2 {
    @Test
    public void testPointcut(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop/springaop.xml");
        Landlord2 landlord = context.getBean("landlord2", Landlord2.class);
        landlord.service();
    }

    @Test
    public void testPointcut2(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop/springaop.xml");
        Landlord2 landlord = context.getBean("landlord2", Landlord2.class);
        landlord.service2();
    }

}
