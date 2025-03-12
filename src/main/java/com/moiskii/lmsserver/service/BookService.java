package com.moiskii.lmsserver.service;

import com.moiskii.lmsserver.exception.BookFoundException;
import com.moiskii.lmsserver.exception.BookNotFoundException;
import com.moiskii.lmsserver.model.Book;

import java.util.List;

public interface BookService {
    Book add(Book book) throws BookFoundException;
    Book update(String isbn, Book book) throws BookNotFoundException;
    Book delete(String isbn) throws BookNotFoundException;
    void delete(Book book) throws BookNotFoundException;
    void deleteAll();
    Book findBook(String isbn) throws BookNotFoundException;
    List<Book> findAll();


}
