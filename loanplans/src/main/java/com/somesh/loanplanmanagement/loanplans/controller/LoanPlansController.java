package com.somesh.loanplanmanagement.loanplans.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;
import com.somesh.loanplanmanagement.loanplans.dto.LoanPlansDto;
import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;
import com.somesh.loanplanmanagement.loanplans.exception.ResourceNotFoundException;
import com.somesh.loanplanmanagement.loanplans.service.ILoanPlansService;

import jakarta.validation.Valid;

@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class LoanPlansController {

    Logger logger=LoggerFactory.getLogger(LoanPlansController.class);
    @Autowired
    private ILoanPlansService loanPlansService;
    @Autowired
	private ModelMapper modelMapper;

    @GetMapping(path = "/loanplans", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<LoanPlansDto> getAllLoanPlans() {
        logger.info("Loan Plans fetched successfully");
        return loanPlansService.getAllLoanPlans().stream().map(loanPlan -> modelMapper.map(loanPlan, LoanPlansDto.class)).
        collect(Collectors.toList());

    }

    @GetMapping(path = "/loanplans/{planid}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<LoanPlansDto> getLoanPlansById(@PathVariable int planid) throws ResourceNotFoundException {
        LoanPlans loanPlan = loanPlansService.getLoanPlanById(planid);
        LoanPlansDto loanPlanDto = modelMapper.map(loanPlan, LoanPlansDto.class);
        logger.info("Loan Plan fetched successfully");
        return new ResponseEntity<LoanPlansDto>(loanPlanDto, HttpStatus.OK);
    }

    @PostMapping(path = "/loanplan", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<LoanPlansDto> createLoanPlans(@RequestBody LoanPlansDto loanPlansDto) throws Exception {
        LoanPlans loanPlan = modelMapper.map(loanPlansDto, LoanPlans.class);
        LoanPlans loanPlanRequest = loanPlansService.createLoanPlan(loanPlan);
        LoanPlansDto loanPlanResponse = modelMapper.map(loanPlanRequest, LoanPlansDto.class);
        logger.info("Loan Plan created successfully");
        return new ResponseEntity<LoanPlansDto>(loanPlanResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/loanplan/{planId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<LoanPlansDto> updateLoanPlans(@PathVariable int planId,
            @Valid @RequestBody LoanPlansDto loanPlansDto) throws ResourceNotFoundException {
        LoanPlans loanPlan = modelMapper.map(loanPlansDto, LoanPlans.class);
        LoanPlans loanPlanRequest = loanPlansService.updateLoanPlan(loanPlan, planId);
        LoanPlansDto loanPlanResponse = modelMapper.map(loanPlanRequest, LoanPlansDto.class);
        logger.info("Loan Plan updated successfully");
        return new ResponseEntity<LoanPlansDto>(loanPlanResponse, HttpStatus.OK);
    }

}
