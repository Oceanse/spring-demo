package com.demo.annotation.autowire;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public void insert(){
        System.out.println("数据添加成功");
    }
}
