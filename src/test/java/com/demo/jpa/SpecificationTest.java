package com.demo.jpa;

import com.demo.jpa.dao.BookRepository;
import com.demo.jpa.model.Book;
import com.demo.jpa.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * 有时我们在查询某个实体的时候，给定的条件是不固定的，如果一个条件就写一个方法，会出现方法爆炸；
 * 这时就需要构根据不同的条件动态灵活地组合查询查询逻辑，在Spring Data JPA中可以通过JpaSpecificationExecutor接口动态构造查询条件。
 * 相比JPQL,其优势是类型安全,更加的面向对象。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jpa/applicationContext.xml")
public class SpecificationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Test
    public void testFindByTitle() {
        Specification<Book> specification = new Specification<Book>() {
            /**
             *
             * @param root 代表了查询的实体类的根节点，通常是你查询的目标实体类的实例，在本例中，root 是一个 Book 类型的 Root 对象，因为它指向 Book 实体类
             * @param criteriaQuery 顶层查询对象，自定义查询方式（了解：一般不用）
             * @param criteriaBuilder 它是用来构建查询谓词的主要工具，，例如等于 (equal)、大于 (greaterThan)、小于 (lessThan)  and、or、not 等,Hibernate 会在后台自动将这些谓词转换为 JPQL 查询，最终，JPQL 查询会被进一步转换为 SQL 查询语句，并发送给数据库执行。
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> title = root.get("title");
                Predicate predicate = criteriaBuilder.equal(title, "vue");//它表示 Book 实体类中的 title 属性是否等于vue;Hibernate 会在后台自动将这些谓词转换为 JPQL 查询，最终，JPQL 查询会被进一步转换为 SQL 查询语句，并发送给数据库执行。
                return predicate;
            }
        };
        List<Book> books = bookRepository.findAll(specification);
        System.out.println(books);
    }

    /**
     * 多条件查询
     */
    @Test
    public void testFindByIdAndTitle() {
        Specification<Book> specification = new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> title = root.get("title");
                Path<Object> id = root.get("id");
                Predicate predicateTitle = criteriaBuilder.equal(title, "vue");
                Predicate predicateId = criteriaBuilder.equal(id, 4);
                Predicate predicate = criteriaBuilder.and(predicateId, predicateTitle);
                return predicate;
            }
        };
        Optional<Book> book = bookRepository.findOne(specification);
        System.out.println(book);
    }

    /**
     * 模糊查询
     * 案例：完成根据客户名称的模糊匹配，返回客户列表
     * 书名称以 ’v‘ 开头
     */
    @Test
    public void testFindByTitleLike() {
        //构造查询条件
        Specification<Book> spec = new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("title");
                //查询方式：模糊匹配
                Predicate like = cb.like(custName.as(String.class), "v%");
                return like;
            }
        };
        List<Book> list = bookRepository.findAll(spec);
        for (Book book : list) {
            System.out.println(book);
        }
    }



    /**
     * 模糊查询+排序
     */
    @Test
    public void testFindByTitleLikeAnsSort() {
        //构造查询条件
        Specification<Book> spec = new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查询属性：客户名
                Path<Object> custName = root.get("title");
                //查询方式：模糊匹配
                Predicate like = cb.like(custName.as(String.class), "v%");
                return like;
            }
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "title");
        List<Book> list = bookRepository.findAll(spec,sort);
        for (Book book : list) {
            System.out.println(book);
        }
    }



    /**
     * 模糊查询+分页
     */
    @Test
    public void testFindByTitleLikeAnsPage() {
        //构造查询条件
        Specification<Book> spec = new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查询属性：客户名
                Path<Object> custName = root.get("title");
                //查询方式：模糊匹配
                Predicate like = cb.like(custName.as(String.class), "v%");
                return like;
            }
        };
        PageRequest pageRequest=PageRequest.of(0,2);
        Page<Book> page = bookRepository.findAll(spec,pageRequest);
        long totalElements = page.getTotalElements();
        List<Book> content = page.getContent();
        System.out.println("totalElements = " + totalElements);
        System.out.println("content = " + content);
    }


    @Test
    public void testDynamicQuery(){
        Map<String,Object> param=new HashMap<>();
        param.put("title","java");
        param.put("author","ocean");
        List<Book> books = bookService.searchBooks(param);
        System.out.println("books = " + books);
    }


}
