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
public class Edition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int edition_id;
    private int num_pages;
    private boolean free;

    @ManyToOne (fetch = FetchType.EAGER)
    private Book book;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "edition")
    @JsonIgnore

    private List<Borrowing> borrowings;

    public Edition(Integer num_pages, Book book, boolean b) {
        this.num_pages=num_pages;
        this.book=book;
        this.free=b;
    }
}
