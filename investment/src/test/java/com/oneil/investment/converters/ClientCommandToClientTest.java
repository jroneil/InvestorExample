package com.oneil.investment.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.oneil.investment.command.ClientCommand;
import com.oneil.investment.entity.Client;

class ClientCommandToClientTest {

	ClientCommandToClient converter;
	
	

	@BeforeEach
	public void setUp() throws Exception {
		converter = new ClientCommandToClient();
	}

	@Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }
	
	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new ClientCommand()));
	}

	@Test
    public void convert() throws Exception {
    	ClientCommand clientCommand=new ClientCommand();
    	clientCommand.setClientName("clientName");
    	clientCommand.setDescription("description");
    	clientCommand.setId(1l);
    	Client client=converter.convert(clientCommand);
    	assertNotNull(clientCommand);
        assertEquals("clientName", client.getClientName());
        assertEquals("description", client.getDescription());
        assertEquals(1l, client.getId());
        
    }
}
