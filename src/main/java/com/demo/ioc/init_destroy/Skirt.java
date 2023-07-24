package com.demo.ioc.init_destroy;


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

    public void setColor(String color) {
        System.out.println("setColor(String color)方法被调用");
        this.color = color;
    }

    public void init(){
        System.out.println("Skirt.init()被调用,给裙子绣花");
    }

    public void show(){
        System.out.println("skirt color is "+color+" and beautiful xiuhua");
    }

    public void destroy(){
        System.out.println("Skirt.destroy被调用，裙子不好看，扔了");
    }

    @Override
    public String toString() {
        return "Skirt{" +
                "color='" + color + '\'' +
                '}';
    }
}
