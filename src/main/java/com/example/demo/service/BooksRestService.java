package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.CountBorrowings;
import com.example.demo.model.Genre;
import org.springframework.data.domain.Page;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface BooksRestService {
    public Page<Book> getAllBooks(int page, int size);
    public List<Book> getAllBooks();
    public void delete_Book(Integer id);
    public Book editBook (Integer id, String title, String plot, Genre genre, List<Author>authors);
    public Optional<Book>findBookById(Integer id);
    public Book createBook(Book book);
    List<Book> searchAllBooks(String term);
    List<CountBorrowings> getBorrowings();




}
