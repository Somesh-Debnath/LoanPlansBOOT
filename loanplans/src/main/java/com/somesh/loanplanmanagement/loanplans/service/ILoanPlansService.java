package com.somesh.loanplanmanagement.loanplans.service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.somesh.loanplanmanagement.loanplans.dto.LoanPlansDto;
import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;
import com.somesh.loanplanmanagement.loanplans.exception.ResourceNotFoundException;

public interface ILoanPlansService {

    LoanPlans createLoanPlan(LoanPlans loanPlan);

    LoanPlans updateLoanPlan(LoanPlans loanPlan, Integer id) throws ResourceNotFoundException;

    Optional<LoanPlans> getLoanPlanById(int id) throws ResourceNotFoundException;

    List<LoanPlans> getAllLoanPlans();

    int calculateInterestAmount(LoanPlans loanPlan, BaseInterestRates baseInterestRates);

    int calculateTotalPayable(LoanPlans loanPlan, BaseInterestRates baseInterestRates);

}
