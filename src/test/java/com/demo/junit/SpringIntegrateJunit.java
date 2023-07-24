package com.demo.junit;

import com.demo.jpa.dao.BookRepository;
import com.demo.jpa.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Spring整合junit
 */
//这种写法是为了让测试在Spring容器环境下执行。
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jpa/applicationContext.xml")
public class SpringIntegrateJunit {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testFindAll() {
        List<Book> allBooks = bookRepository.findByNameWithSQL("vue");
        System.out.println(allBooks);
    }
}
