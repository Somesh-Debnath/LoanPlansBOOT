package com.somesh.loanplanmanagement.loanplans.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loanplanshistory")
public class LoanPlansHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String updatedDate;
    private String updateReason;
    private int LoanPlanId;

    // @ManyToOne
    // @JoinColumn(name = "LoanPlanId", insertable = false, updatable = false)
    // private LoanPlans loanplans;

}
