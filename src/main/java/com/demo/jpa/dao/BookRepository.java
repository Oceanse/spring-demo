package com.demo.jpa.dao;

import com.demo.jpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {//Book是要管理的实体，Long是主键的数据类型。
}