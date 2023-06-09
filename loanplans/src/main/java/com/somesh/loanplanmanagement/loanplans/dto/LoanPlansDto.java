package com.somesh.loanplanmanagement.loanplans.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanPlansDto {

    private int planId;
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
    
    /*PlanName, LoanTypeId, PrincipalAmount, Tenure, InterestRate, InterestAmount, TotalPayable, EMI, PlanValidity, PlanAddedOn) values ('Home Loan', 1, 100000, 12, 8.5, 8500, 108500, 9041.67, '2021-09-30', '2021-09-30');     
     * covert this to json with camelcase
     */

     /*
      * {
    "planName": "Home Loan",
    "loanTypeId": 1,
    "principalAmount": 100000,
    "tenure": 12,
    "interestRate": 8.5,
    "interestAmount": 8500,
    "totalPayable": 108500,
    "emi": 9041.67,
    "planValidity": "2021-09-30",
    "planAddedOn": "2021-09-30"
      }
      */
}
