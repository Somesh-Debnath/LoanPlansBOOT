package com.somesh.loanplanmanagement.loanplans.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;
import com.somesh.loanplanmanagement.loanplans.exception.ResourceNotFoundException;
import com.somesh.loanplanmanagement.loanplans.repository.BaseInterestRatesRepository;
import com.somesh.loanplanmanagement.loanplans.repository.LoanPlansRepository;

@Service
public class LoanPlansService implements ILoanPlansService {
    @Autowired
    private LoanPlansRepository loanPlansRepository;
    @Autowired
    private BaseInterestRatesRepository baseInterestRatesRepository;

    public LoanPlansService(LoanPlansRepository loanPlansRepository) {
        super();
        this.loanPlansRepository = loanPlansRepository;
    }
    @Override
    public int calculateInterestAmount(LoanPlans loanPlan, BaseInterestRates baseInterestRates) {
        int interestAmount = 0;
        float baseInterestRate = baseInterestRates.getBaseInterestRate();
        float interestRate = 0;
        int tenure = loanPlan.getTenure();
        if(tenure>999){
            throw new IllegalArgumentException("Tenure cannot be more than 3 digits");
        }
        double principleAmount = loanPlan.getPrincipalAmount();
        String type = baseInterestRates.getLoanType();
        if (type.equalsIgnoreCase("Personal")) {
            interestRate = (float) (baseInterestRate + (tenure * 0.2));
        } else if (type.equalsIgnoreCase("Home")) {
            interestRate = (float) (baseInterestRate + (tenure * 0.3));
        } else if (type.equalsIgnoreCase("Vehicle")) {
            interestRate = (float) (baseInterestRate + (tenure * 0.25));
        } else if (type.equalsIgnoreCase("Medical")) {
            interestRate = (float) (baseInterestRate + (tenure * 0.25));
        } else {
            interestRate = baseInterestRate;
        }
        loanPlan.setInterestRate(interestRate);
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
        if(loanPlan.getPlanValidity().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Plan validity cannot be before today's date");
        }
        return totalPayable;
    }


    @Override
    public LoanPlans createLoanPlan(LoanPlans loanPlan)  { 
        calculateTotalPayable(loanPlan, baseInterestRatesRepository.findById(loanPlan.getLoanTypeId()).get());
        return loanPlansRepository.save(loanPlan);
    }

    @Override
    public LoanPlans updateLoanPlan(LoanPlans loanPlan, Integer planid) throws ResourceNotFoundException {
        LoanPlans loanPlans=this.loanPlansRepository.findById(planid)
        .orElseThrow(()-> new ResourceNotFoundException("Loan Plan not found for this id :: " + planid));
        calculateTotalPayable(loanPlan, baseInterestRatesRepository.findById(loanPlan.getLoanTypeId()).get());
        loanPlans.setPlanName(loanPlan.getPlanName());
        loanPlans.setLoanTypeId(loanPlan.getLoanTypeId());
        loanPlans.setPrincipalAmount(loanPlan.getPrincipalAmount());
        loanPlans.setTenure(loanPlan.getTenure());
        loanPlans.setInterestRate(loanPlan.getInterestRate());
        loanPlans.setInterestAmount(loanPlan.getInterestAmount());
        loanPlans.setTotalPayable(loanPlan.getTotalPayable());
        loanPlans.setEMI(loanPlan.getEMI());
        loanPlans.setPlanValidity(loanPlan.getPlanValidity());
        loanPlans.setPlanAddedOn(loanPlan.getPlanAddedOn());
        
        return loanPlansRepository.save(loanPlans);
        
    }

    @Override
    public LoanPlans getLoanPlanById(int planid) throws ResourceNotFoundException {
        LoanPlans loanPlans=null;
        if(loanPlansRepository.findById(planid).isPresent()){
            loanPlans=loanPlansRepository.findById(planid).get();
        }
        else{
            throw new ResourceNotFoundException("Loan Plan not found for this id :: " + planid);
        }
        return loanPlans;
       
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
