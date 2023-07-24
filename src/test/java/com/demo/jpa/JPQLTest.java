package com.demo.jpa;

import com.demo.jpa.dao.BookRepository;
import com.demo.jpa.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

//这种写法是为了让测试在Spring容器环境下执行。
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jpa/applicationContext.xml")
public class JPQLTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    public void testFindAll(){
        List<Book> allBooks = bookRepository.findAllBooksWithJPQL();
        System.out.println(allBooks);
    }

    /*
     * springDataJpa中使用jpql完成 更新/删除操作
     *   需要手动添加事务的支持
     *   默认会执行结束之后，回滚事务,  @Rollback(false)不自动回滚
     * */
    @Transactional
    @Rollback(false)
    @Test
    public void testFindBookByTitle(){
        List<Book> allBooks = bookRepository.findBookByTitleWithJPQL("vue");
        System.out.println(allBooks);
    }


    @Test
    public void testUpdateBook(){
        bookRepository.updateBookWithJPQL(3L,"js");

    }
}
