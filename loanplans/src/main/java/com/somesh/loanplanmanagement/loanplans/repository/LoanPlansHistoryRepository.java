package com.somesh.loanplanmanagement.loanplans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.somesh.loanplanmanagement.loanplans.entity.LoanPlansHistory;

public interface LoanPlansHistoryRepository extends JpaRepository<LoanPlansHistory, Integer>{
    
}
