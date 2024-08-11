package com.demo.jpa.service;

import com.demo.jpa.dao.BookRepository;
import com.demo.jpa.dao.BookSpecification;
import com.demo.jpa.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> searchBooks(Map<String, Object> parameters) {
        BookSpecification spec = new BookSpecification(parameters);
        return bookRepository.findAll(spec);
    }
}