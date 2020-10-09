package com.demo.ioc.init_destory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;



/**
 *  在构造函数之后，立刻执行init方法
 *  如果Spring容器没有执行close方法，则不执行销毁方法
 *  如果Spring容器执行了close方法，在执行该方法之前要执行销毁方法
 */
public class Init_DestoryTest {



    @Test
    public void test(){

        //Stage指定为懒加载(使用时候才加载)，这里不会执行初始化方法
        //Skirt是多例模式，因此只能是懒加载(使用时候才加载)，这里不会执行初始化方法
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/init_destory.xml");

    }





    @Test
    public void test2(){

        //Stage指定为懒加载(使用时候才加载)，这里会执行初始化方法
        //Skirt是多例模式，因此只能是懒加载(使用时候才加载)，这里不会执行初始化方法
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/init_destory.xml");
        Stage stage = applicationContext.getBean("stage", Stage.class);
        stage.perform();
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }




    @Test
    public void test3(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/init_destory.xml");
        applicationContext.getBean("skirt", Skirt.class);//bean初始化
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }






}
