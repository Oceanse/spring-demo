package com.demo.di.demo4;

public class Person {

    public Person() {
        System.out.println("Person() is called");
    }

    public Person(Computer computer) {
        System.out.println("Person(Computer computer) is called");
        this.computer = computer;
    }

    private String name;
    private int age;
    private Computer computer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        System.out.println("Person.setComputer(Computer computer) is called");
        this.computer = computer;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", computer=" + computer +
                '}';
    }
}
