package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BorrowingRestService {
    public Page<Borrowing> getAllBorrowings(int page, int size);

    public void delete_Borrowing(Integer id);
    public Borrowing editBorrowing (Integer borrowingId,  LocalDate from, LocalDate to,  Member member, Edition edition,
                                    Employee employee, Boolean active);
    public Optional<Borrowing>findBorrowingById(Integer id);
    public Borrowing createBorrowing(Borrowing borrowing);
    public void endBorrowing(Integer id);
    List<Borrowing> searchBorrowings(Integer parseInt);



}
