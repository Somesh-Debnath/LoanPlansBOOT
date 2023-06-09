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

    @Autowired
    private ModelMapper modelMapper;
    
    

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
    public LoanPlansDto createLoanPlan(LoanPlansDto loanPlan) throws Exception {        
        calculateTotalPayable(this.dtoToLoanPlans(loanPlan), new BaseInterestRates());
        if(loanPlan.getPlanValidity().isBefore(LocalDate.now())){
            throw new Exception("Plan validity should be greater than current date");        }
        if(loanPlan.getTenure()>999){
            throw new Exception("Tenure should be a maximum of 3 digits only");
        }

        LoanPlans loanPlans=this.dtoToLoanPlans(loanPlan);
        return this.loanPlansToDto(loanPlansRepository.save(loanPlans));
    }

    @Override
    public LoanPlansDto updateLoanPlan(LoanPlansDto loanPlan, Integer id) throws ResourceNotFoundException {
        LoanPlans loanPlans=this.loanPlansRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Loan Plan not found for this id :: " + id));
        
        loanPlans.setPlanName(loanPlan.getPlanName());
        loanPlans.setLoanTypeId(loanPlan.getLoanTypeId());
        loanPlans.setPrincipalAmount(loanPlan.getPrincipalAmount());
        loanPlans.setTenure(loanPlan.getTenure());
        loanPlans.setInterestRate(loanPlan.getInterestRate());
        //loanPlans.setInterestAmount(loanPlan.getInterestAmount());
        loanPlans.setPlanValidity(loanPlan.getPlanValidity());
        final LoanPlans updatedLoanPlan = loanPlansRepository.save(loanPlans);
        return this.loanPlansToDto(updatedLoanPlan);
        
    }

    @Override
    public Optional<LoanPlansDto> getLoanPlanById(int planid) throws ResourceNotFoundException {
        
       LoanPlans loanPlans=this.loanPlansRepository.findById(planid)
         .orElseThrow(()-> new ResourceNotFoundException("Loan Plan not found for this id :: " + planid));
         return Optional.of(this.loanPlansToDto(loanPlans));
    }

    @Override
    public List<LoanPlansDto> getAllLoanPlans() {
        List<LoanPlans> loanPlans=this.loanPlansRepository.findAll();
        return loanPlans.stream().map(this::loanPlansToDto).toList();
    }
    
    public LoanPlans dtoToLoanPlans(LoanPlansDto loanPlan) {
        return modelMapper.map(loanPlan, LoanPlans.class);
    }

    public LoanPlansDto loanPlansToDto(LoanPlans loanPlan) {
        return modelMapper.map(loanPlan, LoanPlansDto.class);
    }
}
