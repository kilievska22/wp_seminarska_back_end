package com.example.demo.repository;

import com.example.demo.model.Book;
import com.example.demo.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface GenresJpaRepository extends JpaRepository<Genre, Integer> {
    Page<Genre> findAllByOrderByTitle(Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Genre g set g.title=:title, g.description=:description, g.period=:period where g.genre_id=:genre_id")
    void updateGenre(@Param("genre_id")Integer genre_id, @Param("title")String title, @Param("description")String description, @Param("period")Long period);

    @Query("select g from Genre g WHERE g.title like :term ")
    List<Genre> searchGenres(@Param("term") String term);
    @Query("select g from Genre g  WHERE g.period like :parseLong ")
    List<Genre> searchGenresDate(long parseLong);
    @Query("select g.title, count(b) from Borrowing b inner join b.edition e inner join e.book bo inner join bo.genre g group by g.title")
    List<Object[]> getBorrowings();

}

