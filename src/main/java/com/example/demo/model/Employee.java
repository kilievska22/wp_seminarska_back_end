package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    private int ESSN;
    private String name;
    private int working_time;
    private int salary;
    private String position;
    private String email;
    private String phone;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
@JsonIgnore
    private List<Borrowing> borrowings;

    public Employee(Integer ESSN, String name,  Integer working_time, Integer salary, String position, String phone, String email) {
   this.name=name;
   this.ESSN=ESSN;
   this.working_time=working_time;
   this.salary=salary;
   this.position=position;
   this.phone=phone;
   this.email=email;


    }
}
