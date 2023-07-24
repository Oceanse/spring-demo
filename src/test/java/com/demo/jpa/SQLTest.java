package com.demo.jpa;

import com.demo.jpa.dao.BookRepository;
import com.demo.jpa.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * SQL语句查询
 * 特有的查询：需要在dao接口上配置方法
 * 在新添加的方法上，使用注解的形式配置sql查询语句
 * 注解 ：@Query
 * value : jpql语句 | sql语句
 * nativeQuery : false（使用jpql查询） | true（使用本地查询 ：sql查询）
 */
//这种写法是为了让测试在Spring容器环境下执行。
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jpa/applicationContext.xml")
public class SQLTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testFindAll(){
        List<Book> allBooks = bookRepository.findByNameWithSQL("vue");
        System.out.println(allBooks);
    }
}
