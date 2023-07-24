package com.demo.ioc.produce_bean;



/**
 * 实例工厂实例化bean
 */
public class InstanceFactory {

    public InstanceFactory() {
        System.out.println("实例工厂空参构造 public InstanceFactory() 被调用 ");
    }

    public Mouse getMouse() {
        System.out.println("实例工厂方法 getMouse() 被调用");
        return new Mouse();
    }

}
