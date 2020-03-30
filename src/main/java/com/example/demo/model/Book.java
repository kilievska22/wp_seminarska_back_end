package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;
    private String title;
    private String plot;
    private int num_editions;
    @JsonIgnore
    @OneToMany ( mappedBy = "book")
    private List<Edition> editions;
    @ManyToMany
    private List<Author> authors;
    @ManyToOne
    private Genre genre;




    public Book(Integer id, String title, String plot) {
        this.book_id=id;
        this.title=title;
        this.plot=plot;
    }

    public Book(String title, String plot, Genre genre, List<Author>authors) {
        this.title=title;
        this.plot=plot;
        this.genre=genre;
        this.authors=authors;

    }
}
