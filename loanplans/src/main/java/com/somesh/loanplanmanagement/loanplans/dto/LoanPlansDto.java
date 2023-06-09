package com.somesh.loanplanmanagement.loanplans.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanPlansDto {

    private String PlanName;
    private int LoanTypeId;
    private int PrincipalAmount;
    private int Tenure;
    private float InterestRate;
    private int InterestAmount;
    private int TotalPayable;
    private float EMI;
    private LocalDate PlanValidity;
    private LocalDate PlanAddedOn;
    

}
