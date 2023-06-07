package com.somesh.loanplanmanagement.loanplans.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
@Entity
@Table(name = "loanplans")
public class LoanPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int PlanId;
    private String PlanName;
    private int LoanTypeId;
    private int PrincipalAmount;
    private int Tenure;
    private float InterestRate;
    private int InterestAmount;
    private int TotalPayable;
    private float EMI;
    private Date PlanValidity;
    private Date PlanAddedOn;
    private int baseinterest_id;

    //generate fake json metadata based on above for post request
    // {
    //     "PlanId": 1,
    //     "PlanName": "string",
    //     "LoanTypeId": 1,
    //     "PrincipalAmount": 1,
    //     "Tenure": 1,
    //     "InterestRate": 1,
    //     "InterestAmount": 1,
    //     "TotalPayable": 1,
    //     "EMI": 1,
    //     "PlanValidity": "2021-09-09",
    //     "PlanAddedOn": "2021-09-09",
    //     "baseinterest_id": 1
    // }



    public int getPlanId() {
        return PlanId;
    }
    public void setPlanId(int planId) {
        PlanId = planId;
    }
    public String getPlanName() {
        return PlanName;
    }
    public void setPlanName(String planName) {
        PlanName = planName;
    }
    public int getLoanTypeId() {
        return LoanTypeId;
    }
    public void setLoanTypeId(int loanTypeId) {
        LoanTypeId = loanTypeId;
    }
    public int getPrincipalAmount() {
        return PrincipalAmount;
    }
    public void setPrincipalAmount(int principalAmount) {
        PrincipalAmount = principalAmount;
    }
    public int getTenure() {
        return Tenure;
    }
    public void setTenure(int tenure) {
        Tenure = tenure;
    }
    public float getInterestRate() {
        return InterestRate;
    }
    public void setInterestRate(float interestRate) {
        InterestRate = interestRate;
    }
    public int getInterestAmount() {
        return InterestAmount;
    }
    public void setInterestAmount(int interestAmount) {
        InterestAmount = interestAmount;
    }
    public int getTotalPayable() {
        return TotalPayable;
    }
    public void setTotalPayable(int totalPayable) {
        TotalPayable = totalPayable;
    }
    public float getEMI() {
        return EMI;
    }
    public void setEMI(float eMI) {
        EMI = eMI;
    }
    public Date getPlanValidity() {
        return PlanValidity;
    }
    public void setPlanValidity(Date planValidity) {
        PlanValidity = planValidity;
    }
    public Date getPlanAddedOn() {
        return PlanAddedOn;
    }
    public void setPlanAddedOn(Date planAddedOn) {
        PlanAddedOn = planAddedOn;
    }
    public int getBaseinterest_id() {
        return baseinterest_id;
    }
    public void setBaseinterest_id(int baseinterest_id) {
        this.baseinterest_id = baseinterest_id;
    }


    // @ManyToOne
    // @JoinColumn(name = "baseinterest_id", insertable = false, updatable = false)

    // private BaseInterestRates baseinterestrates;

    // @OneToMany(mappedBy = "loanplans")
    // private List<LoanPlansHistory> loanplanshistory;

}
