package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    private String name;
    private String date_of_birth;
    private String biography;
    @JsonIgnore
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Author(String name, String date_of_birth, String biography, Genre genre) {
        this.name=name;
        this.date_of_birth=date_of_birth;
        this.biography=biography;
        this.genre=genre;
    }
}
