package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Genre;
import com.example.demo.model.countBorrowingsAuthors;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorsRestService {
    public Page<Author> getAllAuthors(int page, int size);
    public void delete_Author(Integer id);
    public Author editAuthor (Integer id, String name_sname, String date_of_birth, String biography, Genre genre);
    public Optional<Author>findAuthorById(Integer id);
    public Author createAuthor(Author author);

    List<Author> searchAllAuthors(String term);
    List<countBorrowingsAuthors> getBorrowings();


}
