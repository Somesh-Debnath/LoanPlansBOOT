package com.somesh.loanplanmanagement.loanplans.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "baseinterestrates")
public class BaseInterestRates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String LoanType;
    private float BaseInterestRate;

    // insert into baseinterestrates (LoanType, BaseInterestRate) values ('Home', 8.5);
    // insert into baseinterestrates (LoanType, BaseInterestRate) values ('Personal', 10);
    // insert into baseinterestrates (LoanType, BaseInterestRate) values ('Medical', 7.5);
    // insert into baseinterestrates (LoanType, BaseInterestRate) values ('Vehicle', 8.0);
    


}
