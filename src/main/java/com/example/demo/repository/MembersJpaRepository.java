package com.example.demo.repository;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Employee;
import com.example.demo.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface MembersJpaRepository extends JpaRepository<Member, Integer> {
    Page<Member> findAllByOrderByName(Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Member m set m.name=:name,  m.membership_start=:membership_start, m.membership_expiration=:membership_expiration, m.email=:email,m.phone=:phone where m.ESSN=:ESSN")
    void updateMember(@Param("ESSN")Integer ESSN, @Param("name")String name,
                        @Param("membership_start")LocalDate membership_start, @Param("membership_expiration")LocalDate membership_expiration, @Param("email")String email, @Param("phone")String phone);

    @Query("select m from Member m WHERE m.name like :term ")
    List<Member> searchMember(@Param("term") String term);
    @Query("select m from Member m  WHERE m.ESSN like :parseInt ")
    List<Member> searchMemberEssn(Integer parseInt);
}

