package com.demo.ioc.lazy_init;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;


/**
 * ApplicationContext代表spring容器
 * 懒加载机制只对单例bean有作用
 * 默认情况下(不设置lazy-init=true/false), 单例bean在创建spring容器（框架）启动时就会马上创建的(非懒加载，实时加载)
 * 如果设置lazy-init=true， 单例bean在创建spring容器启动时不会被创建，只在使用时候才会被实例化
 * lazy-init对于多例bean设置懒加载没有作用意义，scope=prototype时候，只能是懒加载， 多例bean在容器启动时不会被实例化，只能在使用时候实例化
 */
public class CreateBeanTimeTest {

    //容器启动时不会加载Pants对象
    @Test
    public void test() {

        //启动spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/lazy_init/lazy_init.xml");
    }


    //使用对象时会加载Pants对象
    @Test
    public void test2() {

        //启动spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/lazy_init/lazy_init.xml");

        //通过id获取容器中的Pants对象，此时才会创建Pants对象
        applicationContext.getBean("pants", Pants.class);
    }


    @Test
    public void test3() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/lazy_init/lazy_init.xml");
        //通过id获取容器中的Hat对象，此时才会创建Hat对象
        applicationContext.getBean("hat", Hat.class);
    }
}
