package com.demo.ioc.produce_bean;



/**
 * 实例工厂实例化bean
 */
public class InstanceFactory {

    public Mouse getMouse() {
        System.out.println("实例工厂方法 getMouse() is invoked");
        return new Mouse();
    }

}
