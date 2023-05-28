package com.somesh.loanplanmanagement.loanplans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;

public interface LoanPlansRepository extends JpaRepository<LoanPlans, Integer> {

}
