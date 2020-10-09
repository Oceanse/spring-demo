package com.demo.dependency_inject.demo1;

public class Person {
    String name = "ocean";
    int id = 29;
    Book book;

    public Person() {
        System.out.println("person空参构造被调用");
    }


    public void setId(int id) {
        System.out.println("person setId 被调用");
        this.id = id;
    }

    public void setName(String name) {
        System.out.println("person setName 被调用");
        this.name = name;
    }


    public void setBook(Book book) {
        System.out.println("person setBook 被调用");
        this.book = book;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", book=" + book +
                '}';
    }
}
