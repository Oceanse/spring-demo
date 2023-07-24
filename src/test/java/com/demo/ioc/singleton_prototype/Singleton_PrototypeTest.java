package com.demo.ioc.singleton_prototype;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class Singleton_PrototypeTest {

    @Test
    public void Test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/singleton_prototype/singleton_prototype.xml");
        Wine wine = context.getBean("wine", Wine.class);
        Wine wine2 = context.getBean("wine", Wine.class);
        System.out.println(wine == wine2);

    }


    @Test
    public void Test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/singleton_prototype/singleton_prototype.xml");
        Milk milk1 = (Milk) context.getBean("milk");
        Milk milk2 = context.getBean("milk", Milk.class);
        System.out.println(milk1 == milk2);

    }

}
