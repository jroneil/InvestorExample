package com.oneil.investment.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.oneil.investment.command.InvestorCommand;
import com.oneil.investment.entity.Investor;

class InvestorCommandToInvestorTest {
	InvestorCommandToInvestor converter;
	
	
	
	@BeforeEach
	public void setUp() throws Exception {
		converter = new InvestorCommandToInvestor();
	}
	@Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new InvestorCommand()));
	}

	@Test
    public void convert() throws Exception {
    	InvestorCommand InvestorCommand=new InvestorCommand();
    	InvestorCommand.setName("InvestorName");
    	InvestorCommand.setEmail("email@gmail.com");
    	InvestorCommand.setId(1l);
    	Investor investor=converter.convert(InvestorCommand);
    	assertNotNull(InvestorCommand);
        assertEquals("InvestorName", investor.getName());
        assertEquals("email@gmail.com", investor.getEmail());
        assertEquals(1l, investor.getId());
        
    }
}
