package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowingId;
    @Column(name = "from_time")
    private LocalDate from;

    @Column(name = "to_time")
    private LocalDate to;
    @ManyToOne (fetch = FetchType.EAGER)
    private Member member;
    @ManyToOne (fetch = FetchType.EAGER)
    private Edition edition;
    @ManyToOne (fetch = FetchType.EAGER)
    private Employee employee;
    @JsonIgnore
    @OneToMany (mappedBy = "borrowing", fetch =FetchType.EAGER)
    private List<Penalty>penalties;
    private boolean active;


    public Borrowing(LocalDate from, LocalDate to, Member memberAdd, Edition editionAdd, Employee employeeAdd) {
        this.from=from;
        this.to=to;
        this.member=memberAdd;
        this.edition=editionAdd;
        this.employee=employeeAdd;
        this.active=true;

    }
}
