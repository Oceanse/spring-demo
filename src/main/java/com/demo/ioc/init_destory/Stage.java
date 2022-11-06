package com.demo.ioc.init_destory;


/**
 *  在构造函数之后，也就是bean被实例化之后会立刻执行init-method方法
 *  应用上下文容器关闭时会执行destroy-method方法，只适用于单例
 */
public class Stage {

    public Stage() {
        System.out.println("stage空参构造被调用");
    }
    public void perform(){
        System.out.println("演出开始...");
    }
    public void turnOnLight(){
        System.out.println("演出开始前，开灯...");
    }
    public void turnOffLight(){
        System.out.println("演出结束前，关灯...");
    }
}
