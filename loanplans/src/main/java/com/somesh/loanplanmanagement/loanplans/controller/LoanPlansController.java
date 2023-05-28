package com.somesh.loanplanmanagement.loanplans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;
import com.somesh.loanplanmanagement.loanplans.exception.ResourceNotFoundException;
import com.somesh.loanplanmanagement.loanplans.repository.LoanPlansRepository;
import com.somesh.loanplanmanagement.loanplans.service.ILoanPlansService;

@RequestMapping("/api")
@RestController
public class LoanPlansController {
    @Autowired
    private ILoanPlansService loanPlansService;
    @Autowired
    private LoanPlansRepository loanPlansRepository;

    @GetMapping(path = "/loanplans", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<LoanPlans>> getAllLoanPlans() {
        List<LoanPlans> loanPlans = loanPlansService.getAllLoanPlans();
        return new ResponseEntity<List<LoanPlans>>(loanPlans, HttpStatus.OK);
    }

    @GetMapping(path = "/loanplans/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    LoanPlans findByLoanPlansIdFromDBWithException(@PathVariable int id) throws ResourceNotFoundException {
        LoanPlans loanPlans = loanPlansService.getLoanPlanById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan Plan not found for this id :: " + id));
        System.out.println(id);
        return loanPlans;
    }

    @PostMapping(path = "/loanplans", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<LoanPlans> createLoanPlans(LoanPlans loanPlans) {
        LoanPlans newLoanPlan = loanPlansService.createLoanPlan(loanPlans);
        return new ResponseEntity<LoanPlans>(newLoanPlan, HttpStatus.CREATED);
    }

    @PutMapping(path = "/loanplans/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<LoanPlans> updateLoanPlans(@PathVariable int id, LoanPlans loanPlans)
            throws ResourceNotFoundException {
        LoanPlans updatedLoanPlans = loanPlansService.getLoanPlanById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan Plan not found for this id :: " + id));
        updatedLoanPlans.setPlanName(loanPlans.getPlanName());
        updatedLoanPlans.setLoanTypeId(loanPlans.getLoanTypeId());
        updatedLoanPlans.setPrincipalAmount(loanPlans.getPrincipalAmount());
        updatedLoanPlans.setTenure(loanPlans.getTenure());
        updatedLoanPlans.setInterestRate(loanPlans.getInterestRate());
        updatedLoanPlans.setInterestAmount(loanPlans.getInterestAmount());
        updatedLoanPlans.setTotalPayable(loanPlans.getTotalPayable());
        updatedLoanPlans.setEMI(loanPlans.getEMI());
        updatedLoanPlans.setPlanValidity(loanPlans.getPlanValidity());
        updatedLoanPlans.setPlanAddedOn(loanPlans.getPlanAddedOn());
        updatedLoanPlans.setBaseinterest_id(loanPlans.getBaseinterest_id());
        loanPlansRepository.save(updatedLoanPlans);
        return new ResponseEntity<LoanPlans>(updatedLoanPlans, HttpStatus.OK);
    }

}
