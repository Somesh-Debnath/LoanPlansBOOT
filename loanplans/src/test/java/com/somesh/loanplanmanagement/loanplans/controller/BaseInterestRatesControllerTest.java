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

import com.somesh.loanplanmanagement.loanplans.dto.BaseInterestRatesDto;
import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.service.IBaseInterestRatesService;

@ExtendWith(MockitoExtension.class)
public class BaseInterestRatesControllerTest {
    @Mock
    private IBaseInterestRatesService baseInterestRatesService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BaseInterestRatesController baseInterestRatesController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBaseInterestRates() {
        BaseInterestRates baseInterestRate1 = new BaseInterestRates();
        // Set properties for baseInterestRate1

        BaseInterestRates baseInterestRate2 = new BaseInterestRates();
        // Set properties for baseInterestRate2

        BaseInterestRatesDto baseInterestRateDto1 = new BaseInterestRatesDto();
        // Set properties for baseInterestRateDto1

        BaseInterestRatesDto baseInterestRateDto2 = new BaseInterestRatesDto();
        // Set properties for baseInterestRateDto2

        List<BaseInterestRates> baseInterestRatesList = Arrays.asList(baseInterestRate1, baseInterestRate2);
        List<BaseInterestRatesDto> baseInterestRatesDtoList = Arrays.asList(baseInterestRateDto1, baseInterestRateDto2);

        when(baseInterestRatesService.getAllBaseInterestRates()).thenReturn(baseInterestRatesList);
        when(modelMapper.map(baseInterestRatesList, List.class)).thenReturn(baseInterestRatesDtoList);

        ResponseEntity<List<BaseInterestRatesDto>> result = baseInterestRatesController.getAllBaseInterestRates();

        // Assertions
        // Verify the expected calls were made
        verify(baseInterestRatesService, times(1)).getAllBaseInterestRates();
        verify(modelMapper, times(1)).map(baseInterestRatesList, List.class);

        // Verify the expected result
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(baseInterestRatesDtoList, result.getBody());
    }
}
