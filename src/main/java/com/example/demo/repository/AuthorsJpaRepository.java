package com.example.demo.repository;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface AuthorsJpaRepository extends JpaRepository<Author, Integer> {
    Page<Author> findAllByOrderByName(Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Author a set a.name=:name, a.date_of_birth=:date_of_birth, a.biography=:biography,a.genre=:genre where a.authorId=:authorId")
    void updateAuthor(@Param("authorId")Integer author_id, @Param("name")String name, @Param("date_of_birth")String date_of_birth,
                      @Param("biography")String biography, @Param("genre") Genre genre);
    @Query("select a from Author a inner join a.genre g WHERE a.name like :term or g.title like :term")
    List<Author> searchAuthors(@Param("term") String term);
    @Query("select a.name, count(b) from Borrowing b inner join b.edition e inner join e.book bo inner join bo.authors a group by a.name")
    List<Object[]> getBorrowings();
}
