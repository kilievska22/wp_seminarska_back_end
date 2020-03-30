package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.BooksJpaRepository;
import com.example.demo.repository.BorrowingsJpaRepository;
import com.example.demo.repository.PenaltiesJpaRepository;
import com.example.demo.service.BooksRestService;
import com.example.demo.service.BorrowingRestService;
import com.example.demo.service.PenaltiesRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PenaltyRestServiceImpl  implements PenaltiesRestService {
    private final PenaltiesJpaRepository repository;


    public PenaltyRestServiceImpl(PenaltiesJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Penalty> getAllPenalties(int page, int size) {
        return this.repository.findAllByOrderByGivenAtDesc(PageRequest.of(page, size

        ));
    }
    @Override
    public void delete_Penalty(Integer id){
        this.repository.deleteById(id);

    }




    @Override
    public Optional<Penalty> findPenaltyById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Penalty createPenalty(Penalty penalty) {
        return this.repository.save(penalty);
    }

    @Override
    public Penalty updatePenalty(Integer penaltyId) {
        this.repository.updatePenalty(penaltyId, true);
        return this.repository.findById(penaltyId).get();
    }

    @Override
    public Page<Penalty> getUnpaidTodayPenalties(int page, int size, LocalDate today) {

        return this.repository.getUnpaidTodayPenalties(PageRequest.of(page, size), today);
    }

    @Override
    public List<Penalty> getAllPenalties() {
        return this.repository.findAll();


    }

    @Override
    public List<Penalty> searchPenalty(Integer parseInt) {
        return this.repository.searchPenalty(parseInt);
    }


}
