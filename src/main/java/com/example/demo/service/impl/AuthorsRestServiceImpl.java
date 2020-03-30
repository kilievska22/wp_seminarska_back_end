package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.AuthorsJpaRepository;
import com.example.demo.repository.BooksJpaRepository;
import com.example.demo.service.AuthorsRestService;
import com.example.demo.service.BooksRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorsRestServiceImpl  implements AuthorsRestService {
    private final AuthorsJpaRepository repository;


    public AuthorsRestServiceImpl(AuthorsJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Author> getAllAuthors(int page, int size) {
        return this.repository.findAllByOrderByName(PageRequest.of(page, size

        ));
    }
    @Override
    public void delete_Author(Integer id){
        this.repository.deleteById(id);

    }

    @Override
    public Author editAuthor(Integer id, String name_sname, String date_of_birth, String biography, Genre genre) { ;
        this.repository.updateAuthor(id, name_sname, date_of_birth, biography,genre);
        return this.repository.findById(id).get();

    }

    @Override
    public Optional<Author> findAuthorById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Author createAuthor(Author author) {
        return this.repository.save(author);
    }

    @Override
    public List<Author> searchAllAuthors(String term) {
        return this.repository.searchAuthors(term);
    }

    @Override
    public List<countBorrowingsAuthors> getBorrowings() {
        List<Object[]>pom=this.repository.getBorrowings();
        List<countBorrowingsAuthors>res=new ArrayList<>();
        for(Object [] o:pom){
            res.add(new countBorrowingsAuthors((String)o[0], (Long)o[1]));
        }
        return res;
    }


}
