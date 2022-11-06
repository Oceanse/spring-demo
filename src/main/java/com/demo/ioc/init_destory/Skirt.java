package com.demo.ioc.init_destory;


/**
 *  在构造函数之后，立刻执行init方法
 *  如果Spring容器没有执行close方法，则不执行销毁方法
 *  如果Spring容器执行了close方法，在执行该方法之前要执行销毁方法
 */
public class Skirt {

    public Skirt() {
        System.out.println("skirt空参构造被调用");
    }

    String color;

    public void init(){
        color="red";
        System.out.println("Skirt.init()被调用");
    }

    public void show(){
        System.out.println("skirt color is"+color);
    }

    public void destory(){
        System.out.println("Skirt.destory被调用");
    }

    @Override
    public String toString() {
        return "Skirt{" +
                "color='" + color + '\'' +
                '}';
    }
}
