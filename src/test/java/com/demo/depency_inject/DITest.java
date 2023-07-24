package com.demo.depency_inject;

import com.demo.dependency_inject.demo2.Computer;
import com.demo.dependency_inject.demo1.Student;
import com.demo.dependency_inject.demo3.Solider;
import com.demo.dependency_inject.demo4.Person;
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
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
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
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }


    /**
     * 内部bean: 当一个bean仅被用作另一个bean的属性时，可以声明为一个内部bean
     */
    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_inject/dependency_inject.xml");
        Person person = context.getBean("person", Person.class);
        System.out.println(person);
    }


    @Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_inject/dependency_inject.xml");
        Person person = context.getBean("person2", Person.class);
        System.out.println(person);
    }


    @Test
    public void test5() {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_inject/dependency_inject.xml");
        Person person = context.getBean("person3", Person.class);
        System.out.println(person);
    }


    /**
     * 松耦合，并不是不要耦合。A类依赖B类，A类和B类之间存在紧密耦合，
     * 如果把依赖关系变为A类依赖B的父类B0类，A类就可使用B0类的任意子类，于是就成为了面向接口编程
     * 传入具体哪个武器类，可以在外部xml文件中配置，
     */
    @Test
    public void test6() {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_inject/dependency_inject.xml");
        Solider solider = context.getBean("solider", Solider.class);
        solider.play();
    }
}
