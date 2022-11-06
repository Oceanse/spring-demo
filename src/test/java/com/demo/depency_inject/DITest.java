package com.demo.depency_inject;

import com.demo.dependency_inject.demo2.Computer;
import com.demo.dependency_inject.demo1.Student;
import com.demo.dependency_inject.demo3.Solider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class DITest {


    /**
     * setter注入基本类型、List、Map等复杂属性以及对象类型属性
     */
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_inject/dependency_inject.xml");
        Student person = context.getBean("person", Student.class);
        System.out.println(person);
    }


    /**
     * 构造器注入属性，可以按照参数索引、类型、名称指定构造器参数进行注入依赖
     */
    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_inject/dependency_inject.xml");
        Computer c1 = context.getBean("computerByIndex", Computer.class);
        Computer c2 = context.getBean("computerByType", Computer.class);
        Computer c3 = context.getBean("computerByName", Computer.class);
        System.out.println(c1.toString());
        System.out.println(c2.toString());
        System.out.println(c3.toString());
    }


    /**
     * 松耦合，并不是不要耦合。A类依赖B类，A类和B类之间存在紧密耦合，
     * 如果把依赖关系变为A类依赖B的父类B0类，A类就可使用B0类的任意子类，于是就成为了面向接口编程
     * 传入具体哪个武器类，可以在外部xml文件中配置，
     */
    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_inject/dependency_inject.xml");
        Solider solider = context.getBean("solider", Solider.class);
        solider.play();
    }
}
