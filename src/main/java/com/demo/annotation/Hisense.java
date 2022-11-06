package com.demo.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("hisenseTV")
@Scope("prototype") //scope默认为singleton
class Hisense {
    public void show(){
        System.out.println("It is hisense TV");
    }
}