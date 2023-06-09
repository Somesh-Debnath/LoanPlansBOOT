package com.somesh.loanplanmanagement.loanplans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somesh.loanplanmanagement.loanplans.dto.BaseInterestRatesDto;
import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.service.IBaseInterestRatesService;

@RequestMapping("/api")
@RestController
public class BaseInterestRatesController {
    @Autowired
    private IBaseInterestRatesService baseInterestRatesService;

    @GetMapping(path = "/interestrates", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<BaseInterestRates>> getAllBaseInterestRates() {
        List<BaseInterestRates> baseInterestRates = baseInterestRatesService.getAllBaseInterestRates();
        return new ResponseEntity<List<BaseInterestRates>>(baseInterestRates, HttpStatus.OK);
    }

    @PostMapping(path = "/interestrate")
    public ResponseEntity<BaseInterestRates> createBaseInterestRates(@RequestBody BaseInterestRates baseInterestRates) {
        BaseInterestRates newBaseInterestRates = baseInterestRatesService.createBaseInterestRates(baseInterestRates);
        System.out.println("newBaseInterestRates" + newBaseInterestRates);
        return new ResponseEntity<BaseInterestRates>(newBaseInterestRates, HttpStatus.CREATED);
    }
}