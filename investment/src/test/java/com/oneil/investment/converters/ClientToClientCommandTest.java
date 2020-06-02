package com.oneil.investment.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.oneil.investment.command.ClientCommand;
import com.oneil.investment.entity.Client;
import com.oneil.investment.entity.Investor;

import lombok.extern.slf4j.Slf4j;
@Slf4j
class ClientToClientCommandTest {

	ClientToClientCommand converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new ClientToClientCommand(new InvestorToInvestorCommand());
	}
	
	@Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new Client()));
	}

	@Test
    public void convert() throws Exception {
    	Client client=new Client();
    	client.setClientName("clientName");
    	client.setDescription("description");
    	client.setId(1l);
    	/*
    	 * Setting id is not enough it java thinks it is the 
    	 * same object so the second add to the set fails
    	 */
    	 Investor investor = new Investor();
         investor.setId(2l);
         investor.setName("name1");
         investor.setEmail("email1@g.com");
         Investor investor2 = new Investor();
         investor2.setId(4l);
         investor2.setName("name2");
         investor2.setEmail("email@g.com");

         client.getInvestors().add(investor);
         client.getInvestors().add(investor2);
         log.info("  client.getInvestors().size()"+   client.getInvestors().size());
    	ClientCommand clientCommand=converter.convert(client);
    	assertNotNull(clientCommand);
        assertEquals("clientName", clientCommand.getClientName());
        assertEquals("description", clientCommand.getDescription());
        assertEquals(1l, clientCommand.getId());
        assertEquals(2, clientCommand.getInvestors().size());
       
        
    }
}
