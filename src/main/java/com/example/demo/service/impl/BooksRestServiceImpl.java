package com.example.demo.service.impl;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.CountBorrowings;
import com.example.demo.model.Genre;
import com.example.demo.repository.BooksJpaRepository;
import com.example.demo.service.BooksRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BooksRestServiceImpl  implements BooksRestService {
    private final BooksJpaRepository repository;


    public BooksRestServiceImpl(BooksJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Book> getAllBooks(int page, int size) {
        return this.repository.findAllByOrderByTitle(PageRequest.of(page, size

        ));
    }
@Override
public void delete_Book(Integer id){
       this.repository.deleteById(id);

}

    @Override
    public Book editBook(Integer id, String title, String plot, Genre genre, List<Author> authors) { ;
         this.repository.updateBook(id, title, plot, genre);
         return this.repository.findById(id).get();

    }

    @Override
    public Optional<Book> findBookById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Book createBook(Book book) {
        return this.repository.save(book);
    }

    @Override
    public List<Book> searchAllBooks(String term) {
        return this.repository.searchBooks(term);
    }

    @Override
    public List<CountBorrowings> getBorrowings() {
        List<Object[]>pom=this.repository.getBorrowings();
        List<CountBorrowings>res=new ArrayList<>();
        for(Object [] o:pom){
            res.add(new CountBorrowings((String)o[0], (Long)o[1]));
        }
        return res;
    }

    @Override
    public List<Book> getAllBooks() {
        return this.repository.findAllByOrderByTitle();
    }
}
