package com.example.demo.repository;

import com.example.demo.model.Book;
import com.example.demo.model.Edition;
import com.example.demo.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface EditionsJpaRepository extends JpaRepository<Edition, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Edition e set e.free=:free where e.edition_id=:edition_id")
    void updateEdition(@Param("edition_id")Integer edition_id, @Param("free")Boolean free);


}
