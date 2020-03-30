package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class countBorrowingsGenres {
    private  String Genre;
    private Long num_borrowings;
}
