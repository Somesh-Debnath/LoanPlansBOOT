package com.somesh.loanplanmanagement.loanplans.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;
import com.somesh.loanplanmanagement.loanplans.repository.LoanPlansRepository;

@Service
public class LoanPlansService implements ILoanPlansService {
    @Autowired
    private LoanPlansRepository loanPlansRepository;

    @Override
    public LoanPlans updateLoanPlan(LoanPlans loanPlan) {

        LoanPlans loanPlanToUpdate = loanPlansRepository.findById(loanPlan.getPlanId()).get();
        loanPlanToUpdate.setPlanName(loanPlan.getPlanName());
        loanPlanToUpdate.setLoanTypeId(loanPlan.getLoanTypeId());
        loanPlanToUpdate.setPrincipalAmount(loanPlan.getPrincipalAmount());
        loanPlanToUpdate.setTenure(loanPlan.getTenure());
        loanPlanToUpdate.setInterestRate(loanPlan.getInterestRate());
        loanPlanToUpdate.setInterestAmount(loanPlan.getInterestAmount());
        loanPlanToUpdate.setTotalPayable(loanPlan.getTotalPayable());
        loanPlanToUpdate.setEMI(loanPlan.getEMI());
        loanPlanToUpdate.setPlanValidity(loanPlan.getPlanValidity());
        loanPlanToUpdate.setPlanAddedOn(loanPlan.getPlanAddedOn());
        //loanPlanToUpdate.setBaseinterest_id(loanPlan.getBaseinterest_id());
        return loanPlansRepository.save(loanPlanToUpdate);

    }

    @Override
    public Optional<LoanPlans> getLoanPlanById(int id) {

        return loanPlansRepository.findById(id);
    }

    @Override
    public List<LoanPlans> getAllLoanPlans() {
        return loanPlansRepository.findAll();
    }

    @Override
    public double calculateInterestAmount(LoanPlans loanPlan, BaseInterestRates baseInterestRates) {
        double interestAmount = 0.0;
        double baseInterestRate = baseInterestRates.getBaseInterestRate();
        double interestRate = 0.0;
        int tenure = loanPlan.getTenure();
        double principleAmount = loanPlan.getPrincipalAmount();
        String type = baseInterestRates.getLoanType();
        if (type.equalsIgnoreCase("Personal")) {
            interestRate = baseInterestRate + (tenure * 0.2);
        } else if (type.equalsIgnoreCase("Home")) {
            interestRate = baseInterestRate + (tenure * 0.3);
        } else if (type.equalsIgnoreCase("Vehicle")) {
            interestRate = baseInterestRate + (tenure * 0.25);
        } else if (type.equalsIgnoreCase("Medical")) {
            interestRate = baseInterestRate + (tenure * 0.25);
        } else {
            interestRate = baseInterestRate;
        }
        interestAmount = (principleAmount * interestRate * tenure) / 100;
        return interestAmount;

    }

    @Override
    public LoanPlans createLoanPlan(LoanPlans loanPlan) {
        double totalPayable = 0.0;
        double emi = 0.0;
        BaseInterestRates baseInterestRates = loanPlan.getBaseinterestrates();
        double interestAmount = calculateInterestAmount(loanPlan, baseInterestRates);
        totalPayable = loanPlan.getPrincipalAmount() + interestAmount;
        emi = totalPayable / loanPlan.getTenure();
        loanPlan.setInterestAmount(interestAmount);
        loanPlan.setTotalPayable(totalPayable);
        loanPlan.setEMI(emi);

        return loanPlansRepository.save(loanPlan);
    }
}
