package com.somesh.loanplanmanagement.loanplans.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somesh.loanplanmanagement.loanplans.dto.BaseInterestRatesDto;
import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.service.IBaseInterestRatesService;

@RequestMapping("/api")
@RestController
public class BaseInterestRatesController {
    Logger logger = LoggerFactory.getLogger(BaseInterestRatesController.class);
    @Autowired
    private IBaseInterestRatesService baseInterestRatesService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/interestrates", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<BaseInterestRatesDto>> getAllBaseInterestRates() {
        List<BaseInterestRates> baseInterestRates = baseInterestRatesService.getAllBaseInterestRates();
        List<BaseInterestRatesDto> baseInterestRatesDto = modelMapper.map(baseInterestRates, List.class);
        logger.info("Base Interest Rates fetched successfully");
        return new ResponseEntity<List<BaseInterestRatesDto>>(baseInterestRatesDto, HttpStatus.OK);
    }

}