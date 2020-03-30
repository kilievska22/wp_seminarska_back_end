package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Borrowing;
import com.example.demo.model.Edition;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EditionsRestService {
    public Edition createEdition(Edition edition);
    public List<Edition> getAllEditions();
    public Optional<Edition> findEditionById(Integer id);
    void updateEdition(Integer edition_id, Boolean free);

}
