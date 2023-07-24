package com.demo.dependency_inject.demo2;


/**
 *  构造器注入属性，可以按照参数索引、类型、名称指定构造器参数进行注入依赖
 */
public class Computer {
    private String brand;
    private double price;

    public Computer() {
    }

    public Computer(String brand, double price) {
        System.out.println("Computer(String brand, double price) is called");
        this.brand = brand;
        this.price = price;
    }

    @Override
    public String toString() {
        return "MouseFactory{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
