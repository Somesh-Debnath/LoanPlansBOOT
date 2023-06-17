package com.somesh.loanplanmanagement.loanplans.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;
import com.somesh.loanplanmanagement.loanplans.exception.ResourceNotFoundException;
import com.somesh.loanplanmanagement.loanplans.repository.BaseInterestRatesRepository;
import com.somesh.loanplanmanagement.loanplans.repository.LoanPlansRepository;

public class LoanPlansServiceTest {

    @Mock
    private LoanPlansRepository loanPlansRepository;

    @Mock
    private BaseInterestRatesRepository baseInterestRatesRepository;

    @InjectMocks
    private LoanPlansService loanPlansService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
@Test
void testCalculateInterestAmount() {
    LoanPlans loanPlan = new LoanPlans();
    loanPlan.setTenure(12);
    loanPlan.setPrincipalAmount(100000);

    BaseInterestRates baseInterestRates = new BaseInterestRates();
    baseInterestRates.setBaseInterestRate(10);
    baseInterestRates.setLoanType("Personal");

    int interestAmount = loanPlansService.calculateInterestAmount(loanPlan, baseInterestRates);

    assertEquals(148799, interestAmount);
}

    @Test
    void testCalculateTotalPayable() {
        LoanPlans loanPlan = new LoanPlans();
        loanPlan.setTenure(12);
        loanPlan.setPrincipalAmount(100000);
        loanPlan.setLoanTypeId(1);
        loanPlan.setPlanValidity(LocalDate.now().plusDays(1));

        BaseInterestRates baseInterestRates = new BaseInterestRates();
        baseInterestRates.setBaseInterestRate(10);
        baseInterestRates.setLoanType("Personal");

        when(baseInterestRatesRepository.findById(1)).thenReturn(Optional.of(baseInterestRates));

        int totalPayable = loanPlansService.calculateTotalPayable(loanPlan, baseInterestRates);

        assertEquals(248799, totalPayable);
        assertEquals(20733, loanPlan.getEMI());
    }

    @Test
    void testCreateLoanPlan() {
        LoanPlans loanPlan = new LoanPlans();
        loanPlan.setTenure(12);
        loanPlan.setPrincipalAmount(100000);
        loanPlan.setLoanTypeId(1);
        loanPlan.setPlanValidity(LocalDate.now().plusDays(1));

        BaseInterestRates baseInterestRates = new BaseInterestRates();
        baseInterestRates.setBaseInterestRate(10);
        baseInterestRates.setLoanType("Personal");

        when(baseInterestRatesRepository.findById(1)).thenReturn(Optional.of(baseInterestRates));
        when(loanPlansRepository.save(any(LoanPlans.class))).thenReturn(loanPlan);

        LoanPlans createdLoanPlan = loanPlansService.createLoanPlan(loanPlan);

        assertEquals(loanPlan, createdLoanPlan);
        assertEquals(20733, loanPlan.getEMI());
    }

    @Test
    void testUpdateLoanPlan() throws ResourceNotFoundException {
        int planId = 1;

        LoanPlans existingLoanPlan = new LoanPlans();
        existingLoanPlan.setTenure(12);
        existingLoanPlan.setPrincipalAmount(100000);

        LoanPlans updatedLoanPlan = new LoanPlans();
        updatedLoanPlan.setPlanName("Updated Plan");
        updatedLoanPlan.setTenure(24);
        updatedLoanPlan.setPrincipalAmount(200000);
        updatedLoanPlan.setLoanTypeId(1);
        updatedLoanPlan.setPlanValidity(LocalDate.now().plusDays(1));

        BaseInterestRates baseInterestRates = new BaseInterestRates();
        baseInterestRates.setBaseInterestRate(10);
        baseInterestRates.setLoanType("Personal");

        when(loanPlansRepository.findById(planId)).thenReturn(Optional.of(existingLoanPlan));
        when(baseInterestRatesRepository.findById(1)).thenReturn(Optional.of(baseInterestRates));
        when(loanPlansRepository.save(any(LoanPlans.class))).thenReturn(updatedLoanPlan);

        LoanPlans updatedPlan = loanPlansService.updateLoanPlan(updatedLoanPlan, planId);

        assertEquals("Updated Plan", updatedPlan.getPlanName());
        assertEquals(24, updatedPlan.getTenure());
        assertEquals(200000, updatedPlan.getPrincipalAmount());
        assertEquals(37933, updatedPlan.getEMI());
    }

    @Test
    void testGetLoanPlanById() throws ResourceNotFoundException {
        int planId = 1;
        LoanPlans loanPlan = new LoanPlans();

        when(loanPlansRepository.findById(planId)).thenReturn(Optional.of(loanPlan));

        LoanPlans retrievedPlan = loanPlansService.getLoanPlanById(planId);

        assertEquals(loanPlan, retrievedPlan);
    }

    @Test
    void testGetLoanPlanById_ThrowsResourceNotFoundException() {
        int planId = 1;

        when(loanPlansRepository.findById(planId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            loanPlansService.getLoanPlanById(planId);
        });
    }

    @Test
    void testGetAllLoanPlans() {
        List<LoanPlans> loanPlans = List.of(new LoanPlans(), new LoanPlans());

        when(loanPlansRepository.findAll()).thenReturn(loanPlans);

        List<LoanPlans> retrievedPlans = loanPlansService.getAllLoanPlans();

        assertEquals(loanPlans, retrievedPlans);
    }
}
