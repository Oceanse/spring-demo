package com.demo.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class ChangHongTest {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("annotation/annotation.xml");

//        //如果不设置id，直接使用@Component来创建bean,Spring会默认的为它生成ID,即将类名首字母变为小写。
//        ChangHong tv = applicationContext.getBean("changHong", ChangHong.class);
//        ChangHong tv2 = applicationContext.getBean("changHong", ChangHong.class);
//        System.out.println(applicationContext.containsBean("changHong"));
//        System.out.println(tv==tv2);
//        tv.show();
    }

}