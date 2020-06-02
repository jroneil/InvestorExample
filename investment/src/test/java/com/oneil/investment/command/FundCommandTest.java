package com.oneil.investment.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FundCommandTest {

	FundCommand investorCommand;

    @BeforeEach
    public void setUp(){
    	investorCommand = new FundCommand();
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

   
}
