package com.oneil.investment.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.oneil.investment.command.FundCommand;
import com.oneil.investment.entity.Fund;

class FundCommandToFundTest {
	FundCommandToFund converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new FundCommandToFund();
	}
	@Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new FundCommand()));
	}

	@Test
    public void convert() throws Exception {
    	FundCommand FundCommand=new FundCommand();
    	FundCommand.setName("FundName");
       	FundCommand.setId(1l);
    	Fund fund=converter.convert(FundCommand);
    	assertNotNull(FundCommand);
        assertEquals("FundName", fund.getName());
        assertEquals(1l, fund.getId());
        
    }
}
