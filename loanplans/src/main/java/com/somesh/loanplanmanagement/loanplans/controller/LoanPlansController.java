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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somesh.loanplanmanagement.loanplans.dto.LoanPlansDto;
import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;
import com.somesh.loanplanmanagement.loanplans.exception.ResourceNotFoundException;
import com.somesh.loanplanmanagement.loanplans.repository.LoanPlansRepository;
import com.somesh.loanplanmanagement.loanplans.service.ILoanPlansService;

import jakarta.validation.Valid;

@RequestMapping("/api")
@RestController
public class LoanPlansController {
    @Autowired
    private ILoanPlansService loanPlansService;
    @Autowired
    private LoanPlansRepository loanPlansRepository;

    @GetMapping(path = "/loanplans", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<LoanPlansDto>> getAllLoanPlans() {
        List<LoanPlansDto> loanPlans = loanPlansService.getAllLoanPlans();
        return new ResponseEntity<List<LoanPlansDto>>(loanPlans, HttpStatus.OK);
    }

    @GetMapping(path = "/loanplans/{planid}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public LoanPlansDto findByLoanPlansIdFromDBWithException(@PathVariable int planid) throws ResourceNotFoundException {
        LoanPlansDto loanPlans = loanPlansService.getLoanPlanById(planid)
                .orElseThrow(() -> new ResourceNotFoundException("Loan Plan not found for this id :: " + planid));
        return loanPlans;
    }

    @PostMapping(path = "/loanplan", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<LoanPlansDto> createLoanPlans( @RequestBody LoanPlansDto loanPlans) throws Exception {
        LoanPlansDto loanPlan = loanPlansService.createLoanPlan(loanPlans);
        return new ResponseEntity<LoanPlansDto>(loanPlan, HttpStatus.CREATED);
    }

    @PutMapping(path = "/loanplans/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<LoanPlansDto> updateLoanPlans(@PathVariable int id, LoanPlansDto loanPlans)
            throws ResourceNotFoundException {
        LoanPlansDto updatedLoanPlans = loanPlansService.updateLoanPlan(loanPlans, id);
        return new ResponseEntity<LoanPlansDto>(updatedLoanPlans, HttpStatus.OK);
    }

}
