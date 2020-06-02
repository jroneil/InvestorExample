package com.oneil.investment.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.oneil.investment.command.ClientCommand;
import com.oneil.investment.entity.Client;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class ClientCommandToClient implements Converter<ClientCommand, Client> {
	
	

	@Synchronized
	@Nullable
	@Override
	public Client convert(ClientCommand source) {
		  if (source == null) {
	            return null;
	        }
		  log.info("-----------------------aaa");
	        final Client client = new Client();
	        client.setId(source.getId());
	        client.setClientName(source.getClientName());
	        client.setDescription(source.getDescription());
	          
	    
	        return client;
	}

}