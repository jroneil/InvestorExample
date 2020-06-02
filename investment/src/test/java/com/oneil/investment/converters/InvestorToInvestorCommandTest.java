package com.oneil.investment.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.oneil.investment.command.InvestorCommand;
import com.oneil.investment.entity.Fund;
import com.oneil.investment.entity.Investor;

import lombok.extern.slf4j.Slf4j;
@Slf4j
class InvestorToInvestorCommandTest {
	InvestorToInvestorCommand converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new InvestorToInvestorCommand(new FundToFundCommand());
	}

	@Test
	public void testNullObject() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new Investor()));
	}

	@Test
	public void convert() throws Exception {
		Investor investor = new Investor();
		investor.setName("InvestorName");
		investor.setEmail("Email@email");
		investor.setId(1l);
		Fund fund = new Fund();
		fund.setId(2l);
		fund.setName("name");
		Fund fund2 = new Fund();
		fund2.setId(22l);
		fund2.setName("name1");
		Fund fund3 = new Fund();
		fund3.setId(40l);
		fund3.setName("name2");
		investor.getFunds().add(fund);
		investor.getFunds().add(fund2);
		investor.getFunds().add(fund3);
		InvestorCommand investorCommand = converter.convert(investor);
		assertNotNull(investorCommand);
		assertEquals("InvestorName", investorCommand.getName());
		assertEquals("Email@email", investorCommand.getEmail());
		assertEquals(1l, investorCommand.getId());
		assertEquals(3, investorCommand.getFunds().size());

	}
}
