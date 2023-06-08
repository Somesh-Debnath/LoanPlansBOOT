package com.somesh.loanplanmanagement.loanplans.service;

import java.util.List;

import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;

public interface IBaseInterestRatesService {
    /*return list of interest rates */
    public List<BaseInterestRates> getAllBaseInterestRates();

    public BaseInterestRates createBaseInterestRates(BaseInterestRates baseInterestRates);
}
