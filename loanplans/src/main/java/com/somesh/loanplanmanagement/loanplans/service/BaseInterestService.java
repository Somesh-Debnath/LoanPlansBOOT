package com.somesh.loanplanmanagement.loanplans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.repository.BaseInterestRatesRepository;

@Service
public class BaseInterestService implements IBaseInterestRatesService{
@Autowired
    private BaseInterestRatesRepository baseInterestRatesRepository;
    
    public BaseInterestService(BaseInterestRatesRepository baseInterestRatesRepository) {
        super();
        this.baseInterestRatesRepository = baseInterestRatesRepository;
    }
    @Override
    public List<BaseInterestRates> getAllBaseInterestRates() {
       return baseInterestRatesRepository.findAll();
    }
    // @Override
    // public BaseInterestRates createBaseInterestRates(BaseInterestRates baseInterestRates) {
    //     return baseInterestRatesRepository.save(baseInterestRates);
    // }
}
