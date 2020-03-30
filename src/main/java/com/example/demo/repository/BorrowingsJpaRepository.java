package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface BorrowingsJpaRepository extends JpaRepository<Borrowing, Integer> {
    Page<Borrowing> findAllByOrderByFrom(Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Borrowing b set b.from=:from, b.to=:to, b.member=:member, b.edition=:edition, b.employee=:employee,b.active=:active where b.borrowingId=:borrowingId")
    void updateBorrowing(@Param("borrowingId")Integer borrowingId, @Param("from") LocalDate from, @Param("to")LocalDate to, @Param("member") Member member, @Param("edition")Edition edition,
                         @Param("employee")Employee employee, @Param("active")Boolean active);



    @Modifying
    @Transactional
    @Query("UPDATE Borrowing b set b.active=:active where b.borrowingId=:borrowingId")
    void endBorrowing(@Param("borrowingId")Integer borrowingId, @Param("active")Boolean active);
    @Query("select b from Borrowing b inner join  b.member m inner join b.employee e WHERE  m.ESSN = :parseInt or e.ESSN=:parseInt")
    List<Borrowing> searchBorrowings(@Param("parseInt")Integer parseInt);

}
