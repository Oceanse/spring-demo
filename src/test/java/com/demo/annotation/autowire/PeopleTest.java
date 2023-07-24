package com.demo.annotation.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class PeopleTest {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("annotation/annotation.xml");

        People people = applicationContext.getBean("people", People.class);
        people.showCellphone();
    }

}