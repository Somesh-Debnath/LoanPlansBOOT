package com.somesh.loanplanmanagement.loanplans.service;

import java.util.List;
import java.util.Optional;

import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;

public interface ILoanPlansService {

    public LoanPlans createLoanPlan(LoanPlans loanPlan);

    public LoanPlans updateLoanPlan(LoanPlans loanPlan);

    public Optional<LoanPlans> getLoanPlanById(int id);

    public List<LoanPlans> getAllLoanPlans();

    public double calculateInterestAmount(LoanPlans loanPlan, BaseInterestRates baseInterestRates);

}
