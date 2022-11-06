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
        Landlord landlord = context.getBean("landlord", Landlord.class);
        landlord.service();
    }

}
