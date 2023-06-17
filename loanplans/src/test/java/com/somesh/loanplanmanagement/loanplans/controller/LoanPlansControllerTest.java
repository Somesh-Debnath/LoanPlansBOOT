package com.somesh.loanplanmanagement.loanplans.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.somesh.loanplanmanagement.loanplans.dto.LoanPlansDto;
import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;
import com.somesh.loanplanmanagement.loanplans.exception.ResourceNotFoundException;
import com.somesh.loanplanmanagement.loanplans.service.ILoanPlansService;

@ExtendWith(MockitoExtension.class)
public class LoanPlansControllerTest {
    @Mock
    private ILoanPlansService loanPlansService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LoanPlansController loanPlansController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLoanPlans() {
        LoanPlans loanPlan1 = new LoanPlans();
        loanPlan1.setPlanId(1);
        // Set other properties for loanPlan1

        LoanPlans loanPlan2 = new LoanPlans();
        loanPlan2.setPlanId(2);
        // Set other properties for loanPlan2

        LoanPlansDto loanPlanDto1 = new LoanPlansDto();
        // Set properties for loanPlanDto1

        LoanPlansDto loanPlanDto2 = new LoanPlansDto();
        // Set properties for loanPlanDto2

        List<LoanPlans> loanPlansList = Arrays.asList(loanPlan1, loanPlan2);
        List<LoanPlansDto> loanPlansDtoList = Arrays.asList(loanPlanDto1, loanPlanDto2);

        when(loanPlansService.getAllLoanPlans()).thenReturn(loanPlansList);
        when(modelMapper.map(loanPlan1, LoanPlansDto.class)).thenReturn(loanPlanDto1);
        when(modelMapper.map(loanPlan2, LoanPlansDto.class)).thenReturn(loanPlanDto2);

        List<LoanPlansDto> result = loanPlansController.getAllLoanPlans();

        // Assertions
        // Verify the expected calls were made
        verify(loanPlansService, times(1)).getAllLoanPlans();
        verify(modelMapper, times(1)).map(loanPlan1, LoanPlansDto.class);
        verify(modelMapper, times(1)).map(loanPlan2, LoanPlansDto.class);

        // Verify the expected result
        assertEquals(loanPlansDtoList, result);
    }

    @Test
    void testGetLoanPlansById() throws ResourceNotFoundException {
        int planId = 1;

        LoanPlans loanPlan = new LoanPlans();
        loanPlan.setPlanId(planId);
        // Set other properties for loanPlan

        LoanPlansDto loanPlanDto = new LoanPlansDto();
        // Set properties for loanPlanDto

        when(loanPlansService.getLoanPlanById(planId)).thenReturn(loanPlan);
        when(modelMapper.map(loanPlan, LoanPlansDto.class)).thenReturn(loanPlanDto);

        ResponseEntity<LoanPlansDto> result = loanPlansController.getLoanPlansById(planId);

        // Assertions
        // Verify the expected calls were made
        verify(loanPlansService, times(1)).getLoanPlanById(planId);
        verify(modelMapper, times(1)).map(loanPlan, LoanPlansDto.class);

        // Verify the expected result
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(loanPlanDto, result.getBody());
    }

    @Test
    void testCreateLoanPlans() throws Exception {
        LoanPlansDto loanPlanDto = new LoanPlansDto();
        // Set properties for loanPlanDto

        LoanPlans loanPlan = new LoanPlans();
        // Set properties for loanPlan

        when(modelMapper.map(loanPlanDto, LoanPlans.class)).thenReturn(loanPlan);
        when(loanPlansService.createLoanPlan(loanPlan)).thenReturn(loanPlan);
        when(modelMapper.map(loanPlan, LoanPlansDto.class)).thenReturn(loanPlanDto);

        ResponseEntity<LoanPlansDto> result = loanPlansController.createLoanPlans(loanPlanDto);

        // Assertions
        // Verify the expected calls were made
        verify(modelMapper, times(1)).map(loanPlanDto, LoanPlans.class);
        verify(loanPlansService, times(1)).createLoanPlan(loanPlan);
        verify(modelMapper, times(1)).map(loanPlan, LoanPlansDto.class);

        // Verify the expected result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(loanPlanDto, result.getBody());
    }

    @Test
    void testUpdateLoanPlans() throws ResourceNotFoundException {
        int planId = 1;

        LoanPlansDto loanPlanDto = new LoanPlansDto();
        // Set properties for loanPlanDto

        LoanPlans loanPlan = new LoanPlans();
        // Set properties for loanPlan

        when(modelMapper.map(loanPlanDto, LoanPlans.class)).thenReturn(loanPlan);
        when(loanPlansService.updateLoanPlan(loanPlan, planId)).thenReturn(loanPlan);
        when(modelMapper.map(loanPlan, LoanPlansDto.class)).thenReturn(loanPlanDto);

        ResponseEntity<LoanPlansDto> result = loanPlansController.updateLoanPlans(planId, loanPlanDto);

        // Assertions
        // Verify the expected calls were made
        verify(modelMapper, times(1)).map(loanPlanDto, LoanPlans.class);
        verify(loanPlansService, times(1)).updateLoanPlan(loanPlan, planId);
        verify(modelMapper, times(1)).map(loanPlan, LoanPlansDto.class);

        // Verify the expected result
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(loanPlanDto, result.getBody());
    }
}
