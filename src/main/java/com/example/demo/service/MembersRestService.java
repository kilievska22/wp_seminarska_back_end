package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Employee;
import com.example.demo.model.Member;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MembersRestService {
    public Page<Member> getAllMembers(int page, int size);
    public void delete_Member(Integer id);
    public Member editMember (Integer ESSN, String name,
    LocalDate membership_start, LocalDate membership_expiration,String email, String phone);

    public Optional<Member>findMemberById(Integer id);
    public Member createMember(Member member);

    List<Member> searchAllMembers(String term);


}
