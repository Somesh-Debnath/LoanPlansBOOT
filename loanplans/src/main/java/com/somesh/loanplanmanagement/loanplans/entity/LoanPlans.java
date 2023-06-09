package com.somesh.loanplanmanagement.loanplans.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    
    @Column(name = "PlanName" , columnDefinition = "varchar(255) default 'string'")
    private String PlanName;

    @Column(name = "LoanTypeId" , columnDefinition = "int")
    private int LoanTypeId;

    @Column(name = "PrincipalAmount" , columnDefinition = "int")
    private int PrincipalAmount;

    @Column(name = "Tenure" , columnDefinition = "int")
    private int Tenure;

    @Column(name = "InterestRate" , columnDefinition = "float")
    private float InterestRate;

    @Column(name = "InterestAmount" , columnDefinition = "int")
    private int InterestAmount;

    @Column(name = "TotalPayable" , columnDefinition = "int")
    private int TotalPayable;

    @Column(name = "EMI" , columnDefinition = "float")
    private float EMI;

    @Column(name = "PlanValidity" , columnDefinition = "date")
    private LocalDate PlanValidity;

    @Column(name = "PlanAddedOn" , columnDefinition = "date")
    private LocalDate PlanAddedOn;


    @ManyToOne
    @JoinColumn(name = "loanTypeId", insertable = false, updatable = false)

    private BaseInterestRates baseinterestrates;

    @OneToMany(mappedBy = "loanplans",fetch = FetchType.EAGER)
    private List<LoanPlansHistory> loanplanshistory;

    // Seed the data into the LoanPlans table 
    // create sql query
    // insert into loanplans (PlanName, LoanTypeId, PrincipalAmount, Tenure, InterestRate, InterestAmount, TotalPayable, EMI, PlanValidity, PlanAddedOn) values ('Home Loan', 1, 100000, 12, 8.5, 8500, 108500, 9041.67, '2021-09-30', '2021-09-30');
    //return the above query with underscores in the column names in lowercase
    // insert into loanplans (plan_name, loan_type_id, principal_amount, tenure, interest_rate, interest_amount, total_payable, emi, plan_validity, plan_added_on) values ('Home Loan', 1, 100000, 12, 8.5, 8500, 108500, 9041.67, '2021-09-30', '2021-09-30');

}
