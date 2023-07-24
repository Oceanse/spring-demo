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
 * 方法命名规则查询
 * 是对jpql查询，更加深入的一层封装
 * 我们只需要按照SpringDataJpa提供的方法名称规则来定义方法，不需要再去配置jpql语句，就能完成查询
 */
//这种写法是为了让测试在Spring容器环境下执行。
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jpa/applicationContext.xml")
public class MethodNameTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    public void testFindByTitle(){
        List<Book> books = bookRepository.findByTitle("vue");
        System.out.println(books);
    }

    @Test
    public void testFindByIdAndTitle(){
        Book book = bookRepository.findByIdAndTitle(3L,"vue");
        System.out.println(book);
    }

    @Test
    public void testFindByTitleLike(){
        List<Book> books = bookRepository.findByTitleLike("spring%");
        System.out.println(books);
    }
}
