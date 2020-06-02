package com.oneil.investment.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.oneil.investment.command.FundCommand;
import com.oneil.investment.entity.Fund;

class FundToFundCommandTest {
	FundToFundCommand converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new FundToFundCommand();
	}
	
	@Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new Fund()));
	}

	@Test
    public void convert() throws Exception {
		Fund fund=new Fund();
    	fund.setName("FundName");
    	fund.setId(1l);
    	

    	FundCommand fundCommand=converter.convert(fund);
    	assertNotNull(fundCommand);
        assertEquals("FundName", fundCommand.getName());
         assertEquals(1l, fundCommand.getId());
	}     
}
