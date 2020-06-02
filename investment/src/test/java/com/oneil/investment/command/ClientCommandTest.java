package com.oneil.investment.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientCommandTest {
	ClientCommand investorCommand;

    @BeforeEach
    public void setUp(){
    	investorCommand = new ClientCommand();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 4L;

        investorCommand.setId(idValue);

        assertEquals(idValue, investorCommand.getId());
    }

    @Test
    public void getName() throws Exception {
    	 String nameValue = "Joe Smith";

         investorCommand.setClientName(nameValue);

         assertEquals(nameValue, investorCommand.getClientName());
    }

    @Test
    public void getDescription() throws Exception {
  
    String descVal = "This a description";

    investorCommand.setDescription(descVal);

    assertEquals(descVal, investorCommand.getDescription());
    }
    @Test
    public void getInvestorsNull() throws Exception {
  
   
    investorCommand.setInvestors(null);

    assertNull(investorCommand.getInvestors());
    }
    @Test
    public void getGreaterThanZero() throws Exception {
    	InvestorCommand newInvestor=new InvestorCommand();
    	newInvestor.setId(1l);
    	newInvestor.setName("Joe Smoe");
    	newInvestor.setEmail("js@gmail.com");
    	newInvestor.setClientId(1l);
    	investorCommand.getInvestors().add(newInvestor);
    
    assertNotNull(investorCommand.getInvestors());
    assertEquals(1, investorCommand.getInvestors().size());
    }
    
	
}
