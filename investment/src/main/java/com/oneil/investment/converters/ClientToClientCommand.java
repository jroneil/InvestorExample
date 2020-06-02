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
public class ClientToClientCommand implements Converter<Client, ClientCommand> {
private final InvestorToInvestorCommand investorConverter;
	
	
	public ClientToClientCommand(InvestorToInvestorCommand investorConverter) {
		super();
		this.investorConverter = investorConverter;
	}


	@Synchronized
	@Nullable
	@Override
	public ClientCommand convert(Client source) {
		  if (source == null) {
	            return null;
	        }

	        final ClientCommand clientCommand = new ClientCommand();
	        clientCommand.setId(source.getId());
	        clientCommand.setClientName(source.getClientName());
	        clientCommand.setDescription(source.getDescription());
	      

	        if (source.getInvestors() != null && source.getInvestors().size() > 0){
	            source.getInvestors()
	                    .forEach(investor -> clientCommand.getInvestors().add(investorConverter.convert(investor)));
	        }
	        log.info("ClientToClientCommand-----------------end");
	        return clientCommand;
	    }
	}


