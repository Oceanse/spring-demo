package com.demo.jpa.dao;

import com.demo.jpa.model.Book;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Specification 进行动态查询，这种模式允许你在运行时根据不同的条件灵活地组合查询逻辑
 * Specification<T>是Spring Data JPA提供的一个接口，它定义了如何构建复杂的查询条件。这个接口的主要作用是允许用户通过组合多个查询条件来创建复杂的查询逻辑，而无需编写原始SQL，这使得代码更加可维护和易于扩展。
 *
 * 在 JPA 中，当你使用 Criteria API（CriteriaQuery 和 CriteriaBuilder ） 来构建查询时，Hibernate 会在后台自动将这些谓词转换为 JPQL 查询，最终，JPQL 查询会被进一步转换为 SQL 查询语句，并发送给数据库执行。
 */
public class BookSpecification implements Specification<Book> {

    private Map<String, Object> parameters;

    public BookSpecification(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        //如果 title 参数存在，这个谓词将被添加到谓词列表中，用于构建最终的查询条件，最终的查询条件会被转换成相应的 SQL 语句。
        if (parameters.containsKey("title")) {
            String title = (String) parameters.get("title");
            Predicate titlePredicate = cb.like(root.get("title"), "%" + title + "%");
            predicates.add(titlePredicate);
        }

        //如果 author 参数存在，这个谓词将被添加到谓词列表中，用于构建最终的查询条件，最终的查询条件会被转换成相应的 SQL 语句。
        if (parameters.containsKey("author")) {
            String author = (String) parameters.get("author");
            Predicate authorPredicate = cb.equal(root.get("author"), author);//它表示 Book 实体类中的 author 属性是否等于传入的 author 参数
            predicates.add(authorPredicate);
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}