package com.somesh.loanplanmanagement.loanplans.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loanplans")
public class LoanPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PlanId;
    private String PlanName;
    private int LoanTypeId;
    private int PrincipalAmount;
    private int Tenure;
    private float InterestRate;
    private int InterestAmount;
    private int TotalPayable;
    private float EMI;
    private Date PlanValidity;
    private Date PlanAddedOn;
    private int baseinterest_id;

    @ManyToOne
    @JoinColumn(name = "baseinterest_id", insertable = false, updatable = false)

    private BaseInterestRates baseinterestrates;

    @OneToMany(mappedBy = "loanplans")
    private List<LoanPlansHistory> loanplanshistory;

}
