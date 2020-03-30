package com.example.demo.service.impl;

import com.example.demo.model.Book;
import com.example.demo.model.CountBorrowings;
import com.example.demo.model.Genre;
import com.example.demo.model.countBorrowingsGenres;
import com.example.demo.repository.BooksJpaRepository;
import com.example.demo.repository.GenresJpaRepository;
import com.example.demo.service.BooksRestService;
import com.example.demo.service.GenresRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenresRestServiceImpl  implements GenresRestService {
    private final GenresJpaRepository repository;


    public GenresRestServiceImpl(GenresJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Genre> getAllGenres(int page, int size) {
        return this.repository.findAllByOrderByTitle(PageRequest.of(page, size

        ));
    }
    @Override
    public void delete_Genre(Integer id){
        this.repository.deleteById(id);

    }

    @Override
    public Genre editGenre( Integer id, String title, String description, Long period) { ;
        this.repository.updateGenre(id, title, description, period);
        return this.repository.findById(id).get();

    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Long.parseLong(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    @Override
    public Optional<Genre> findGenreById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Genre createGenre(Genre genre) {
        return this.repository.save(genre);
    }

    @Override
    public List<Genre> searchAllGenres(String term) {
        if(isNumeric(term)==true){
        return this.repository.searchGenresDate(Long.parseLong(term));}
        else
            return
            this.repository.searchGenres(term);
    }

    @Override
    public List<countBorrowingsGenres> getBorrowings() {
        List<Object[]>pom=this.repository.getBorrowings();
        List<countBorrowingsGenres>res=new ArrayList<>();
        for(Object [] o:pom){
            res.add(new countBorrowingsGenres((String)o[0], (Long)o[1]));
        }
        return res;
    }


}
