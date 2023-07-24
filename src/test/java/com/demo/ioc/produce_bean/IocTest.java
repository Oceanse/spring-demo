package com.demo.ioc.produce_bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class IocTest {


    /**
     * 空参构造创建对象
     */
    @Test
    public void constructTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/produce_bean/Ioc.xml");
        Mouse mouse=(Mouse) context.getBean("mouseByEmptyConstruct");
        mouse.show();
    }


    /**
     *  静态工厂模式创建对象
     */
    @Test
    public void staticFactTest(){
    ApplicationContext context = new ClassPathXmlApplicationContext("ioc/produce_bean/Ioc.xml");
        Mouse m = (Mouse)context.getBean("mouseByStaticFactory");
        m.show();
    }


    /**
     *  实例工厂模式创建对象
     */
    @Test
    public void instanceFactTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/produce_bean/Ioc.xml");
        Mouse m = (Mouse)context.getBean("mouseByInstanceFactory");
        m.show();
    }


    /**
     *通过实现FactoryBean创建工厂bean， 然后生成对象
     */
    @Test
    public void factBeanTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/produce_bean/Ioc.xml");
        Mouse m = (Mouse)context.getBean("mouseByFactoryBean");
        m.show();
    }
}
