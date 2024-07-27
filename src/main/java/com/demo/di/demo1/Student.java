package com.demo.di.demo1;


public class Student {
    String name = "ocean";
    int id = 370784;
    Book book;

    public Student() {
        System.out.println("Student空参构造被调用");
    }


    public void setId(int id) {
        System.out.println("Student setId 被调用");
        this.id = id;
    }

    public void setName(String name) {
        System.out.println("Student setName 被调用");
        this.name = name;
    }


    public void setBook(Book book) {
        System.out.println("Student setBook 被调用");
        this.book = book;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", book=" + book +
                '}';
    }
}
