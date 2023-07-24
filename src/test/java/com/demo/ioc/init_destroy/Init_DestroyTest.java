package com.demo.ioc.init_destroy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;



/**
 *  在构造函数之后，立刻执行init方法
 *  如果Spring容器没有执行close方法，则不执行销毁方法
 *  如果Spring容器执行了close方法，在执行该方法之前要执行销毁方法
 */
public class Init_DestroyTest {

    @Test
    public void test(){

        //Stage指定为懒加载(使用时候才加载)，这里不会执行初始化方法
        //Skirt是多例模式，因此只能是懒加载(使用时候才加载)，这里不会执行初始化方法
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/init_destroy.xml");
    }


    @Test
    public void test2(){

        //Stage指定为懒加载(使用时候才加载)，这里会执行初始化方法
        //Skirt是多例模式，因此只能是懒加载(使用时候才加载)，这里不会执行初始化方法
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/init_destroy.xml");
        Stage stage = applicationContext.getBean("stage", Stage.class);
        stage.perform();
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }


    /**
     * prototype:容器在接受到该类型对象的请求时,会每次都重新生成一个新的对象实例给请求方,该对象的实例化以及属性设置等工作都是由容器负责的,
     * 但是只要准备完毕,并且对象实例返回给请求方的时候,容器就不再拥有当前返回对象的引用,请求方需要自己负责当前返回对象的生命周期管理工作,
     * 也就是说容器不能知道实例的结束时机，也就不能回调销毁方法，因此多例模式 destroy-method方法不会被执行
     */
    @Test
    public void test3(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/init_destroy.xml");
        Skirt skirt = applicationContext.getBean("skirt", Skirt.class);
        skirt.show();
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }






}
