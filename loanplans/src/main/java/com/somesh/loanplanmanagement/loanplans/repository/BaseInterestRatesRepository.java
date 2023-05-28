package com.somesh.loanplanmanagement.loanplans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;

public interface BaseInterestRatesRepository extends JpaRepository<BaseInterestRates, Integer> {
    
}
