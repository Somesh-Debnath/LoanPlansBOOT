package com.somesh.loanplanmanagement.loanplans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.somesh.loanplanmanagement.loanplans.entity.BaseInterestRates;
import com.somesh.loanplanmanagement.loanplans.entity.LoanPlans;
import com.somesh.loanplanmanagement.loanplans.exception.ResourceNotFoundException;
import com.somesh.loanplanmanagement.loanplans.repository.BaseInterestRatesRepository;
import com.somesh.loanplanmanagement.loanplans.repository.LoanPlansRepository;
import com.somesh.loanplanmanagement.loanplans.service.BaseInterestRatesService;
import com.somesh.loanplanmanagement.loanplans.service.LoanPlansService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LoanplansApplicationTests {

	@Autowired
	private LoanPlansService loanPlansService;

	@Autowired
	private BaseInterestRatesService baseInterestRatesService;

	@MockBean
	private LoanPlansRepository loanPlansRepository;

	@MockBean
	private BaseInterestRatesRepository baseInterestRatesRepository;

	@Test
	public void getAllLoanPlansTest(){
		when(loanPlansRepository.findAll()).thenReturn(Stream
		.of(new LoanPlans(),
				new LoanPlans()).collect(Collectors.toList()));
		assertEquals(2, loanPlansService.getAllLoanPlans().size());
	}

	@Test
	public void getLoanPlanByIdTest() throws ResourceNotFoundException{
		when(loanPlansRepository.findById(1))
		.thenReturn(Optional.of(new LoanPlans()));
		assertEquals(new LoanPlans(), loanPlansService.getLoanPlanById(1));
	}

	@Test
	public void createLoanPlanTest() {
		LoanPlans loanPlan = new LoanPlans();
		when(loanPlansRepository.save(loanPlan)).thenReturn(loanPlan);
		assertEquals(loanPlan, loanPlansService.createLoanPlan(loanPlan));
	}

	@Test
	public void updateLoanPlanTest() throws ResourceNotFoundException {
		LoanPlans loanPlan = new LoanPlans(144, "string", 1, 1, 0, 1.0f, 1, 1, 1f, LocalDate.now(), LocalDate.now(), null,
				null);
		when(loanPlansRepository.save(loanPlan)).thenReturn(loanPlan);
		assertEquals(loanPlan, loanPlansService.updateLoanPlan(loanPlan, 144));
	}

	@Test
	public void getAllBaseInterestRatesTest(){
		when(baseInterestRatesRepository.findAll()).thenReturn(Stream
		.of(new BaseInterestRates(),
				new BaseInterestRates()).collect(Collectors.toList()));
		assertEquals(2, baseInterestRatesService.getAllBaseInterestRates().size());
	}

}
