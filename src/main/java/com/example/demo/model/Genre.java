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
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genre_id;
    private String title;
    private String description;
    private Long period;
    @JsonIgnore

    @OneToMany(mappedBy = "genre")
    private List<Book>books;
    @JsonIgnore
    @OneToMany (mappedBy = "genre")
    private List<Author>authors;

    public Genre(String title, String description, Long period) {
        this.title=title;
        this.period=period;
        this.description=description;
    }
}
