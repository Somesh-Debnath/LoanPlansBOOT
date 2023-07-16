package com.somesh.loanplanmanagement.loanplans.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.repository.BaseInterestRatesRepository;

public class BaseInterestRatesServiceTest {
    @Autowired
    private BaseInterestRatesService baseInterestRatesService;

    AutoCloseable autoCloseable;
    @Mock
    private BaseInterestRatesRepository baseInterestRatesRepository;
    BaseInterestRates baseInterestRates;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        baseInterestRatesService = new BaseInterestRatesService(baseInterestRatesRepository);
        baseInterestRates = new BaseInterestRates(1, "Home", 8.5f);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testGetAllBaseInterestRates() {
        mock(BaseInterestRates.class);
        mock(BaseInterestRatesRepository.class);
        List<BaseInterestRates> baseInterestRatesList = baseInterestRatesService.getAllBaseInterestRates();
        assertEquals(baseInterestRatesList.size(), 0);
    }
}
