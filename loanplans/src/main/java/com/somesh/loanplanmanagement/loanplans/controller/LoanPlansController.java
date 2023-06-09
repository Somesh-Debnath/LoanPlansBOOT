package com.somesh.loanplanmanagement.loanplans.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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
import java.util.stream.Collectors;
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
	private ModelMapper modelMapper;

    @GetMapping(path = "/loanplans", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<LoanPlansDto> getAllLoanPlans() {
        return loanPlansService.getAllLoanPlans().stream().map(loanPlan -> modelMapper.map(loanPlan, LoanPlansDto.class)).
        collect(Collectors.toList());

    }

    @GetMapping(path = "/loanplans/{planid}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<LoanPlansDto> getLoanPlansById(@PathVariable int planid) throws ResourceNotFoundException {
        LoanPlans loanPlan = loanPlansService.getLoanPlanById(planid).orElse(null);
        LoanPlansDto loanPlanDto = modelMapper.map(loanPlan, LoanPlansDto.class);
        return new ResponseEntity<LoanPlansDto>(loanPlanDto, HttpStatus.OK);
    }

    @PostMapping(path = "/loanplan", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<LoanPlansDto> createLoanPlans(@RequestBody LoanPlansDto loanPlansDto) throws Exception {
        LoanPlans loanPlan = modelMapper.map(loanPlansDto, LoanPlans.class);
        LoanPlans loanPlanRequest = loanPlansService.createLoanPlan(loanPlan);
        LoanPlansDto loanPlanResponse = modelMapper.map(loanPlanRequest, LoanPlansDto.class);
        return new ResponseEntity<LoanPlansDto>(loanPlanResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/loanplans/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<LoanPlansDto> updateLoanPlans(@PathVariable(value = "id") int planid,
            @Valid @RequestBody LoanPlansDto loanPlansDto) throws ResourceNotFoundException {
        LoanPlans loanPlan = modelMapper.map(loanPlansDto, LoanPlans.class);
        LoanPlans loanPlanRequest = loanPlansService.updateLoanPlan(loanPlan, planid);
        LoanPlansDto loanPlanResponse = modelMapper.map(loanPlanRequest, LoanPlansDto.class);
        return new ResponseEntity<LoanPlansDto>(loanPlanResponse, HttpStatus.OK);
    }

}
