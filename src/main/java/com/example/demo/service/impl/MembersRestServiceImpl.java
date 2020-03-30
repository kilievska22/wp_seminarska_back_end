package com.example.demo.service.impl;

import com.example.demo.model.Book;
import com.example.demo.model.Employee;
import com.example.demo.model.Member;
import com.example.demo.repository.BooksJpaRepository;
import com.example.demo.repository.EmployeesJpaRepository;
import com.example.demo.repository.MembersJpaRepository;
import com.example.demo.service.BooksRestService;
import com.example.demo.service.EmployeesRestService;
import com.example.demo.service.MembersRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
class MembersRestServiceImpl  implements MembersRestService {
    private final MembersJpaRepository repository;


    public MembersRestServiceImpl(MembersJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Member> getAllMembers(int page, int size) {
        return this.repository.findAllByOrderByName(PageRequest.of(page, size

        ));
    }
    @Override
    public void delete_Member(Integer id){
        this.repository.deleteById(id);

    }

    @Override
    public Member editMember(Integer ESSN, String name,
                             LocalDate membership_start, LocalDate membership_expiration,String email, String phone) { ;
        this.repository.updateMember(ESSN, name, membership_start,membership_expiration,email,phone);
        return this.repository.findById(ESSN).get();

    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    @Override
    public Optional<Member> findMemberById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Member createMember(Member member) {
        return this.repository.save(member);
    }

    @Override
    public List<Member> searchAllMembers(String term) {
        if(isNumeric(term)==true){
            return   this.repository.searchMemberEssn(Integer.parseInt(term));}
        else {return this.repository.searchMember(term);}
    }


}
