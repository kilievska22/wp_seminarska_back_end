package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.CountBorrowings;
import com.example.demo.model.Genre;
import com.example.demo.model.countBorrowingsGenres;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface GenresRestService {
    public Page<Genre> getAllGenres(int page, int size);
    public void delete_Genre(Integer id);
    public Genre editGenre ( Integer id, String title, String description, Long period);
    public Optional<Genre>findGenreById(Integer id);
    public Genre createGenre(Genre genre);
    List<Genre> searchAllGenres(String term);

    List<countBorrowingsGenres> getBorrowings();


}
