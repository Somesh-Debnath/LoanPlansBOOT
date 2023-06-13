package com.somesh.loanplanmanagement.loanplans.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;
import com.somesh.loanplanmanagement.loanplans.service.LoanPlansService;

@WebMvcTest(LoanPlansController.class)
public class LoanPlansControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LoanPlansService loanPlansService;
    LoanPlans loanPlansOne;
    LoanPlans loanPlansTwo;
    List<LoanPlans> loanPlansList=new ArrayList<LoanPlans>();

    @BeforeEach
    public void setUp() {
        loanPlansOne = new LoanPlans(1, "Home Loan", 1, 100000, 12, 8.5f, 8500, 108500, 9041.67f,
                LocalDate.parse("2021-09-30"), LocalDate.parse("2023-09-30"));
        loanPlansTwo = new LoanPlans(2, "Loan", 1, 100000, 12, 8.5f, 8500, 108500, 9041.67f,
                LocalDate.parse("2021-09-30"), LocalDate.parse("2023-10-30"));
        loanPlansList.add(loanPlansOne);
        loanPlansList.add(loanPlansTwo);
    }

    @AfterEach
    public void tearDown() throws Exception {
        loanPlansOne = null;
        loanPlansTwo = null;
        loanPlansList = null;
    }

    @Test
    public void testGetAllLoanPlans() throws Exception {
        when(loanPlansService.getAllLoanPlans()).thenReturn(loanPlansList);
        mockMvc.perform(get("/api/v1/loanplans")).andExpect(status().isOk());
    }

    @Test
    public void testGetLoanPlanById() throws Exception {
        when(loanPlansService.getLoanPlanById(1)).thenReturn(loanPlansOne);
        mockMvc.perform(get("/api/v1/loanplans/1")).andExpect(status().isOk());
    }

    @Test
    public void testGetLoanPlanByIdNotFound() throws Exception {
        when(loanPlansService.getLoanPlanById(1)).thenReturn(loanPlansOne);
        mockMvc.perform(get("/api/v1/loanplans/2")).andExpect(status().isNotFound());
    }

    @Test
    public void testAddLoanPlan() throws Exception {
        when(loanPlansService.addLoanPlan(loanPlansOne)).thenReturn(loanPlansOne);
        mockMvc.perform(post("/api/v1/loanplans").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loanPlansOne))).andExpect(status().isCreated());
    }

    @Test
    public void testUpdateLoanPlan() throws Exception {
        when(loanPlansService.updateLoanPlan(loanPlansOne, 1)).thenReturn(loanPlansOne);
        mockMvc.perform(put("/api/v1/loanplans/1").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loanPlansOne))).andExpect(status().isOk());
    }

    @Test
    public void testUpdateLoanPlanNotFound() throws Exception {
        when(loanPlansService.updateLoanPlan(loanPlansOne, 1)).thenReturn(loanPlansOne);
        mockMvc.perform(put("/api/v1/loanplans/2").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loanPlansOne))).andExpect(status().isNotFound());
    }

    
    
}
