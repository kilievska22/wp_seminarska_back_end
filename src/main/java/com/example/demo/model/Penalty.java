package com.example.demo.model;

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
public class Penalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int penaltyId;
    private int price;
    private LocalDate givenAt;
    private LocalDate dueDate;
    private boolean paid;

    @ManyToOne(fetch = FetchType.EAGER)
    private Borrowing borrowing;

    public Penalty(Borrowing borrowingAdd, LocalDate givenAt, LocalDate dueDate, Integer price) {
        this.borrowing=borrowingAdd;
        this.givenAt=givenAt;
        this.dueDate=dueDate;
        this.price=price;
        this.paid=false;
    }
}
