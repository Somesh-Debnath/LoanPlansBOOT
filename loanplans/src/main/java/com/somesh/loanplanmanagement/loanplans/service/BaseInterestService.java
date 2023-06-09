package com.somesh.loanplanmanagement.loanplans.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somesh.loanplanmanagement.loanplans.dto.BaseInterestRatesDto;
import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.repository.BaseInterestRatesRepository;

@Service
public class BaseInterestService implements IBaseInterestRatesService{
@Autowired
    private BaseInterestRatesRepository baseInterestRatesRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<BaseInterestRates> getAllBaseInterestRates() {
       return baseInterestRatesRepository.findAll();
    }
    @Override
    public BaseInterestRatesDto createBaseInterestRates(BaseInterestRatesDto baseInterestRates) {
        BaseInterestRates baseInterestRatesEntity = dtoToBaseInterestRates(baseInterestRates);
        BaseInterestRates savedBaseInterestRates = baseInterestRatesRepository.save(baseInterestRatesEntity);
        return BaseInterestRatesToDto(savedBaseInterestRates);
    }
    
        
    public BaseInterestRates dtoToBaseInterestRates(BaseInterestRatesDto BaseInterestRate) {
        return modelMapper.map(BaseInterestRate, BaseInterestRates.class);
    }

    public BaseInterestRatesDto BaseInterestRatesToDto(BaseInterestRates BaseInterestRate) {
        return modelMapper.map(BaseInterestRate, BaseInterestRatesDto.class);
    }
}
