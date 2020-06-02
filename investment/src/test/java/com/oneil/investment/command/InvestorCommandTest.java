package com.oneil.investment.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvestorCommandTest {

	InvestorCommand investorCommand;

    @BeforeEach
    public void setUp(){
    	investorCommand = new InvestorCommand();
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

         investorCommand.setName(nameValue);

         assertEquals(nameValue, investorCommand.getName());
    }

    @Test
    public void getEmail() throws Exception {
  
    String emailVal = "Joe.Smith@gmail.com";

    investorCommand.setEmail(emailVal);

    assertEquals(emailVal, investorCommand.getEmail());
    }
}
