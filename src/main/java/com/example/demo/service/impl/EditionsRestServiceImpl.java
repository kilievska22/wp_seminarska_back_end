package com.example.demo.service.impl;

import com.example.demo.model.Edition;
import com.example.demo.repository.EditionsJpaRepository;
import com.example.demo.service.EditionsRestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EditionsRestServiceImpl implements EditionsRestService {
    private final EditionsJpaRepository repository;


    public EditionsRestServiceImpl(EditionsJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Edition createEdition(Edition edition) {
        return this.repository.save(edition);
    }

    @Override
    public List<Edition> getAllEditions() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Edition> findEditionById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public void updateEdition(Integer edition_id, Boolean free) {
        this.repository.updateEdition(edition_id,free);
    }
}
