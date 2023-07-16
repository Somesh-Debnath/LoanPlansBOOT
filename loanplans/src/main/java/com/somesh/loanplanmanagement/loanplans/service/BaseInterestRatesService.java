package com.somesh.loanplanmanagement.loanplans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.repository.BaseInterestRatesRepository;

@Service
public class BaseInterestRatesService implements IBaseInterestRatesService {
    @Autowired
    private BaseInterestRatesRepository baseInterestRatesRepository;

    public BaseInterestRatesService(BaseInterestRatesRepository baseInterestRatesRepository) {
        super();
        this.baseInterestRatesRepository = baseInterestRatesRepository;
    }

    @Override
    public List<BaseInterestRates> getAllBaseInterestRates() {
        return baseInterestRatesRepository.findAll();
    }

}
