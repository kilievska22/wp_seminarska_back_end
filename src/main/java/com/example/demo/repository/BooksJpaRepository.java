package com.example.demo.repository;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Borrowing;

import com.example.demo.model.CountBorrowings;
import com.example.demo.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface BooksJpaRepository extends JpaRepository<Book, Integer> {
    Page<Book> findAllByOrderByTitle(Pageable pageable);
    List<Book> findAllByOrderByTitle();
    @Modifying
    @Transactional
    @Query("UPDATE Book b set b.title=:title, b.plot=:plot, b.genre=:genre where b.book_id=:book_id")
    void updateBook(@Param("book_id")Integer book_id, @Param("title")String title, @Param("plot")String plot, @Param("genre") Genre genre);
    @Query("select b from Book b inner join b.genre g  WHERE b.title like :term or g.title like :term ")
    List<Book> searchBooks(@Param("term") String term);
    @Query("select bo.title, count(b) from Borrowing b inner join b.edition e inner join e.book bo group by bo.title")
   List<Object[]> getBorrowings();
}
