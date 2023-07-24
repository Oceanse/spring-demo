package com.demo.jpa;

import com.demo.jpa.dao.BookRepository;
import com.demo.jpa.model.Book;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 有时我们在查询某个实体的时候，给定的条件是不固定的，如果一个条件就写一个方法，会出现方法爆炸；
 * 这时就需要动态构建相应的查询语句，在Spring Data JPA中可以通过JpaSpecificationExecutor接口动态构造查询条件。
 * 相比JPQL,其优势是类型安全,更加的面向对象。
 * <p>
 * public interface JpaSpecificationExecutor<T> {
 * Optional<T> findOne(@Nullable Specification<T> var1);
 * List<T> findAll(@Nullable Specification<T> var1);
 * Page<T> findAll(@Nullable Specification<T> var1, Pageable var2);
 * List<T> findAll(@Nullable Specification<T> var1, Sort var2);
 * long count(@Nullable Specification<T> var1);
 * }
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jpa/applicationContext.xml")
public class SpecificationTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testFindByTitle() {
        Specification<Book> specification = new Specification<Book>() {
            /**
             *
             * @param root 需要比较的属性（path对象）
             * @param criteriaQuery 顶层查询对象，自定义查询方式（了解：一般不用）
             * @param criteriaBuilder 构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配）
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> title = root.get("title");//需要比较的属性
                Predicate predicate = criteriaBuilder.equal(title, "vue");//构造查询条件
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
                //查询属性：客户名
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


}
