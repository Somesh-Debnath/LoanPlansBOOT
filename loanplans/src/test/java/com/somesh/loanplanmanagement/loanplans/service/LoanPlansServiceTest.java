package com.somesh.loanplanmanagement.loanplans.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.somesh.loanplanmanagement.loanplans.dto.LoanPlansDto;
import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;
import com.somesh.loanplanmanagement.loanplans.exception.ResourceNotFoundException;
import com.somesh.loanplanmanagement.loanplans.repository.LoanPlansRepository;

public class LoanPlansServiceTest {
    @InjectMocks
    private LoanPlansService loanPlansService;

    AutoCloseable autoCloseable;
    @Mock
    private LoanPlansRepository loanPlansRepository;
    LoanPlans loanPlans;
    LoanPlans loanPlansDto;

    @InjectMocks
    private ModelMapper modelMapper;

    @Before
    public void setUp1(){
        MockitoAnnotations.initMocks(this);
    }
    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        loanPlansService = new LoanPlansService(loanPlansRepository);
        // loanPlansDto = new LoanPlansDto(1, "Home Loan", 1, 100000, 12, 8.5f, 8500, 108500, 9041.67f,
        //         LocalDate.parse("2021-09-30"), LocalDate.parse("2023-09-30"));
        System.out.println(loanPlans);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testGetAllLoanPlans() {
        mock(LoanPlans.class);
        mock(LoanPlansRepository.class);
        List<LoanPlans> loanPlansList = loanPlansService.getAllLoanPlans();
        assertEquals(loanPlansList.size(), 0);
    }

    @Test
    public void testGetLoanPlanById() throws ResourceNotFoundException {
        mock(LoanPlans.class);
        mock(LoanPlansRepository.class);
        LoanPlans loanPlans = loanPlansService.getLoanPlanById(1);
        assertEquals(loanPlans, 0);
    }

    @Test
    public void testCreateLoanPlan() {
        mock(LoanPlans.class);
        mock(LoanPlansRepository.class);
        LoanPlans loanPlans= new LoanPlans();
        loanPlans.setPlanId(1);
        loanPlans.setPlanName("Home Loan");
        loanPlans.setLoanTypeId(1);
        loanPlans.setPlanAddedOn(LocalDate.parse("2021-09-30"));
        loanPlans.setPlanValidity(LocalDate.parse("2023-09-30"));
        when(loanPlansRepository.save(loanPlans)).thenReturn(loanPlans);
        LoanPlans savedLoanPlans = loanPlansService.createLoanPlan(loanPlans);
        assertEquals(savedLoanPlans, loanPlans);
    }

    @Test
    public void testUpdateLoanPlan() throws ResourceNotFoundException {
        mock(LoanPlans.class);
        mock(LoanPlansRepository.class);
        when(loanPlansRepository.findById(1)).thenReturn(java.util.Optional.of(loanPlans));
        when(loanPlansRepository.save(loanPlans)).thenReturn(loanPlans);
        LoanPlans updatedLoanPlans = loanPlansService.updateLoanPlan(loanPlans, 1);
        assertEquals(updatedLoanPlans, loanPlans);
    }

    // @Test
    // public void testCalculateInterestAmount() {
    //     mock(LoanPlans.class);
    //     mock(LoanPlansRepository.class);
    //     int interestAmount = loanPlansService.calculateInterestAmount(loanPlans, loanPlans.getBaseInterestRates());
    //     assertEquals(interestAmount, 0);
    // }

    // @Test
    // public void testCalculateTotalPayable() {
    //     mock(LoanPlans.class);
    //     mock(LoanPlansRepository.class);
    //     int totalPayable = loanPlansService.calculateTotalPayable(loanPlans, loanPlans.getBaseInterestRates());
    //     assertEquals(totalPayable, 0);
    // }
    
}
