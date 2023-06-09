package com.somesh.loanplanmanagement.loanplans.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somesh.loanplanmanagement.loanplans.dto.LoanPlansDto;
import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;
import com.somesh.loanplanmanagement.loanplans.exception.ResourceNotFoundException;
import com.somesh.loanplanmanagement.loanplans.repository.LoanPlansRepository;

@Service
public class LoanPlansService implements ILoanPlansService {
    @Autowired
    private LoanPlansRepository loanPlansRepository;

    public LoanPlansService(LoanPlansRepository loanPlansRepository) {
        super();
        this.loanPlansRepository = loanPlansRepository;
    }
    @Override
    public int calculateInterestAmount(LoanPlans loanPlan, BaseInterestRates baseInterestRates) {
        int interestAmount = 0;
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
        interestAmount = (int) ((principleAmount * interestRate * tenure) / 100);
        return interestAmount;

    }

    @Override
    public int calculateTotalPayable(LoanPlans loanPlan, BaseInterestRates baseInterestRates) {
        int totalPayable = 0;
        float emi = 0;
        int interestAmount = calculateInterestAmount(loanPlan, baseInterestRates);
        totalPayable = (int) (loanPlan.getPrincipalAmount() + interestAmount);
        emi = totalPayable / loanPlan.getTenure();
        loanPlan.setInterestAmount(interestAmount);
        loanPlan.setTotalPayable(totalPayable);
        loanPlan.setPlanAddedOn(LocalDate.now());
        loanPlan.setEMI(emi);
        return totalPayable;
    }


    @Override
    public LoanPlans createLoanPlan(LoanPlans loanPlan)  { 
        
        return loanPlansRepository.save(loanPlan);
    }

    @Override
    public LoanPlans updateLoanPlan(LoanPlans loanPlan, Integer id) throws ResourceNotFoundException {
        LoanPlans loanPlans=this.loanPlansRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Loan Plan not found for this id :: " + id));
        
        loanPlans.setPlanName(loanPlan.getPlanName());
        loanPlans.setLoanTypeId(loanPlan.getLoanTypeId());
        loanPlans.setPrincipalAmount(loanPlan.getPrincipalAmount());
        loanPlans.setTenure(loanPlan.getTenure());
        loanPlans.setInterestRate(loanPlan.getInterestRate());
        loanPlans.setInterestAmount(loanPlan.getInterestAmount());
        loanPlans.setTotalPayable(loanPlan.getTotalPayable());
        loanPlans.setEMI(loanPlan.getEMI());
        loanPlans.setPlanValidity(loanPlan.getPlanValidity());
        return loanPlansRepository.save(loanPlans);
        
    }

    @Override
    public Optional<LoanPlans> getLoanPlanById(int planid) throws ResourceNotFoundException {
        Optional<LoanPlans> loanPlans=this.loanPlansRepository.findById(planid);
        if(loanPlans.isEmpty()) {
            throw new ResourceNotFoundException("Loan Plan not found for this id :: " + planid);
        } else{
            return loanPlans;
        }
       
    }

    @Override
    public List<LoanPlans> getAllLoanPlans() {
        List<LoanPlans> loanPlans=this.loanPlansRepository.findAll();
        return loanPlans;
    }
    
    // public LoanPlans dtoToLoanPlans(LoanPlansDto loanPlan) {
    //     return modelMapper.map(loanPlan, LoanPlans.class);
    // }

    // public LoanPlansDto loanPlansToDto(LoanPlans loanPlan) {
    //     LoanPlansDto loanPlanDto = new LoanPlansDto();
    //     loanPlanDto.setPlanId(loanPlan.getPlanId());
    //     loanPlanDto.setPlanName(loanPlan.getPlanName());
    //     loanPlanDto.setLoanTypeId(loanPlan.getLoanTypeId());
    //     loanPlanDto.setPrincipalAmount(loanPlan.getPrincipalAmount());
    //     loanPlanDto.setTenure(loanPlan.getTenure());
    //     loanPlanDto.setInterestRate(loanPlan.getInterestRate());
    //     loanPlanDto.setInterestAmount(loanPlan.getInterestAmount());
    //     loanPlanDto.setTotalPayable(loanPlan.getTotalPayable());
    //     loanPlanDto.setEMI(loanPlan.getEMI());
    //     loanPlanDto.setPlanValidity(loanPlan.getPlanValidity());
    //     loanPlanDto.setPlanAddedOn(loanPlan.getPlanAddedOn());
    //     return loanPlanDto;
    // }
}
