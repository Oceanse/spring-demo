package com.demo.ioc.produce_bean;

/**
 * 静态工厂实例化bean
 */
public class StaticFactory {
    public static Mouse getMouse() {
        System.out.println("静态工厂方法 getMouse() is invoked");
        return new Mouse();
    }
}
