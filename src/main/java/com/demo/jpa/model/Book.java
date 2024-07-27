package com.demo.jpa.model;

import javax.persistence.*;

@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//使用数据库的自增列来生成主键值,数据库会自动为每个新插入的记录分配一个唯一的主键值。这意味着不需要手动设置主键的值，数据库会自动处理主键的生成。
    private Long id;

    private String title;


    public Book() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
