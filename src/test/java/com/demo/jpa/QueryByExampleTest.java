package com.demo.jpa;

import com.demo.jpa.dao.BookRepository;
import com.demo.jpa.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * ！在 Spring Data JPA 中，QueryByExample 是一种非常灵活的方式来构建动态查询
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jpa/applicationContext.xml")
public class QueryByExampleTest {
    @Autowired
    BookRepository bookRepository;



    @Test
    public void testQueryByExample(){
        List<Book> all = bookRepository.findAll();
        System.out.println("all = " + all);

        Book book=new Book();
        book.setTitle("java");//当前案例中，title属性被设置成了后缀匹配，所以这里相当于设置了后缀
        book.setAuthor("ocean");

        // ExampleMatcher 来定制查询条件
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("author") // 忽略null值.withIgnoreCase("title")   // 忽略大小写这个方法告诉 ExampleMatcher 在比较时忽略 author 属性。这意味着即使查询中的 author 与数据库中的记录不匹配，该记录也会被包含在结果中。
                .withMatcher("title",ExampleMatcher.GenericPropertyMatchers.endsWith());// title 属性使用了 结尾 字符串匹配模式，这意味着查询将只考虑标题的结尾部分来决定是否匹配。

        // 使用 book 实例和 matcher 创建 Example
        Example<Book> example = Example.of(book, matcher);

        // 执行查询
        List<Book> all2 = bookRepository.findAll(example);
        System.out.println("all2 = " + all2);
    }



    @Test
    public void testQueryByExample2(){
        List<Book> all = bookRepository.findAll();
        System.out.println("all = " + all);

        Book book=new Book();
        book.setTitle("java");

        // ExampleMatcher 来定制查询条件
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains());// title属性使用了包含字符串匹配模式，也就是模糊匹配，查找标题中包含特定关键词的所有书籍

        // 使用 book 实例和 matcher 创建 Example
        Example<Book> example = Example.of(book, matcher);

        // 执行查询
        List<Book> all2 = bookRepository.findAll(example);
        System.out.println("all2 = " + all2);

        //分页
        int page = 0; // 在 Spring Data JPA 中，Pageable 的 page 参数是从 0 开始计数的。page = 0 表示第一页，page = 1 表示第二页
        int size = 2; // 每页显示的数量

        //排序
        Sort sort = Sort.by(Sort.Direction.ASC, "title");

        Pageable pageable = PageRequest.of(page, size,sort);

        // 执行分页排序查询
        Page<Book> all3 = bookRepository.findAll(example,pageable);
        List<Book> books = all3.getContent();
        System.out.println("books = " + books);
    }


}
