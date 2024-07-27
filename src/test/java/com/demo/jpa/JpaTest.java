package com.demo.jpa;

import com.demo.jpa.dao.BookRepository;
import com.demo.jpa.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 使用Spring框架和JUnit进行集成测试的，让测试在Spring容器环境下执行
 * SpringJUnit4ClassRunner是Spring框架提供的一个特殊的运行器，它允许你在JUnit测试中加载Spring的上下文环境
 * ContextConfiguration配置Spring的应用上下文,配置文件通常包含了Spring bean的定义和配置信息
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jpa/applicationContext.xml")
public class JpaTest {

    @Autowired
    BookRepository bookRepository;


    /**
     * 查询
     */
    @Test
    public void testQuery() {
        List<Book> all = bookRepository.findAll();
        System.out.println(all);
    }

    /**
     * save : 保存或者更新
     *      根据传递的对象是否存在主键id，
     *      如果没有id主键属性：保存
     *      存在id主键属性，根据id查询数据，更新数据
     */
    @Test
    public void testInsert() {
        Book book = new Book();
        book.setTitle("spring data jpa2");
        book.setId(1L);
        bookRepository.save(book);//存在相同的id就更新

        Book book2 = new Book();
        book2.setTitle("vue");
        //book2.setId(2L);//实体类设置了主键自增策略，这里可以不设置主键
        bookRepository.save(book2);

        List<Book> all = bookRepository.findAll();
        System.out.println("all========" + all);
    }


    @Test
    public void testUpdate() {
        Book book = new Book();
        book.setId(2L);
        book.setTitle("thinking in java");
        bookRepository.save(book);
    }

    @Test
    public void testDelete() {
        bookRepository.deleteById(2L);
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        List<Book> list = bookRepository.findAll();
        for (Book customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 测试统计查询：查询书籍的总数量
     * count:统计总条数
     */
    @Test
    public void testCount() {
        long count = bookRepository.count();//查询全部的客户数量
        System.out.println(count);
    }


    /**
     * 测试：判断id为4的书籍是否存在
     */
    @Test
    public void  testExists() {
        boolean exists = bookRepository.existsById(4l);
        System.out.println("id为4的客户 是否存在："+exists);
    }


    /**
     * 根据id从数据库查询
     *      @Transactional : 保证getOne正常运行
     *
     *  findOne：
     *      em.find()           :立即加载
     *  getOne：
     *      em.getReference     :延迟加载
     *      * 返回的是一个客户的动态代理对象
     *      * 什么时候用，什么时候查询
     */
    @Test
    @Transactional
    public void  testGetOne() {
        Book book = bookRepository.getOne(4l);
        System.out.println(book);
    }
}
