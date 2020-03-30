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
public class Member {
    @Id
    private int ESSN;
    private String name;
    private LocalDate membership_start;
    private LocalDate membership_expiration;
    private String email;
    private String phone;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "member")
    @JsonIgnore

    private List<Borrowing> borrowings;
    private boolean active_membership;

    public Member(Integer essn, String name, LocalDate membership_start, LocalDate membership_expiration, String email, String phone) {
        this.ESSN=essn;
        this.name=name;
        this.membership_start=membership_start;
        this.membership_expiration=membership_expiration;
        this.email=email;
        this.phone=phone;
    }
}
