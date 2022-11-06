package com.demo.dependency_inject.demo1;

import java.util.List;
import java.util.Map;

public class Book {
    String title;
    List<String> language;
    Map<String, String> contents;

    public Book() {
        System.out.println("Book()......");
    }

    public void setTitle(String title) {
        System.out.println("setTitle 被调用");
        this.title = title;
    }

    public void setLanguage(List<String> language) {
        System.out.println("setLanguage 被调用");
        this.language = language;
    }

    public void setContents(Map<String, String> contents) {
        System.out.println("setContents 被调用");
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", language=" + language +
                ", contents=" + contents +
                '}';
    }
}
