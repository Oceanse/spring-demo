package com.demo.ioc.produce_bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 工厂bean首先实现FactoryBean,FactoryBean的类型形参就是其生产对象的类型
 * 然后重写其方法，在getObject()定义生产的bean
 */
public class MouseFactory implements FactoryBean<Mouse> {
    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }

    /**
     * 定义返回的bean
     * @return
     * @throws Exception
     */
    @Override
    public Mouse getObject(){
        System.out.println("MouseFactory.getObject() 被调用");
        return new Mouse();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
