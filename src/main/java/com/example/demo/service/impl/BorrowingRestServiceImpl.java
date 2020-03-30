package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.BooksJpaRepository;
import com.example.demo.repository.BorrowingsJpaRepository;
import com.example.demo.service.BooksRestService;
import com.example.demo.service.BorrowingRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRestServiceImpl  implements BorrowingRestService {
    private final BorrowingsJpaRepository repository;



    public BorrowingRestServiceImpl(BorrowingsJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Borrowing> getAllBorrowings(int page, int size) {
        return this.repository.findAllByOrderByFrom(PageRequest.of(page, size

        ));
    }
    @Override
    public void delete_Borrowing(Integer id){
        this.repository.deleteById(id);

    }

    @Override
    public Borrowing editBorrowing(Integer borrowingId, LocalDate from, LocalDate to, Member member, Edition edition,
                                    Employee employee, Boolean active) { ;
        this.repository.updateBorrowing(borrowingId, from, to, member,edition,employee,active);
        return this.repository.findById(borrowingId).get();

    }

    @Override
    public Optional<Borrowing> findBorrowingById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Borrowing createBorrowing(Borrowing borrowing) {
        return this.repository.save(borrowing);
    }

    @Override
    public void endBorrowing(Integer id) {
         this.repository.endBorrowing(id, false);

    }

    @Override
    public List<Borrowing> searchBorrowings(Integer parseInt) {
        return this.repository.searchBorrowings(parseInt);
    }


}
