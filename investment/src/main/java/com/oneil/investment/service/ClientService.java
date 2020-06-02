package com.oneil.investment.service;

import java.util.Set;

import com.oneil.investment.command.ClientCommand;
import com.oneil.investment.entity.Client;

public interface ClientService {
	Set<Client> getClients();

	Client findById(Long l);

	ClientCommand findCommandById(Long l);

	ClientCommand saveClientCommand(ClientCommand command);

	ClientCommand updateClientCommand(ClientCommand command);

	void deleteById(Long idToDelete);

	Long count();

}