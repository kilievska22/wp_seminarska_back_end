package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PenaltiesRestService {
    public Page<Penalty> getAllPenalties(int page, int size);

    public void delete_Penalty(Integer id);

    public Optional<Penalty>findPenaltyById(Integer id);
    public Penalty createPenalty(Penalty penalty);
    Penalty updatePenalty(Integer penaltyId);
    Page<Penalty> getUnpaidTodayPenalties(int page,int size ,LocalDate today);
    List<Penalty> getAllPenalties();


    List<Penalty> searchPenalty(Integer parseInt);
}
