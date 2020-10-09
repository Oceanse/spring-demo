package com.demo.dependency_inject.demo1;


public class Teacher {

    private String Varity;

    public Teacher() {
        super();
        System.out.println("Teacher空参构造被调用");
    }

    public void info(){
        System.out.println("techer......");
    }
}
