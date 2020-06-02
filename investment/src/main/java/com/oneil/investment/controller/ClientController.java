package com.oneil.investment.controller;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneil.investment.command.ClientCommand;
import com.oneil.investment.command.InvestorCommand;
import com.oneil.investment.entity.Client;
import com.oneil.investment.entity.Investor;
import com.oneil.investment.exception.ClientNotFoundException;
import com.oneil.investment.exception.InvestorNotFoundException;
import com.oneil.investment.service.ClientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/clients")
@Slf4j
public class ClientController {

	private ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@GetMapping("/all")
	public ResponseEntity<Set<Client>> allClients() {
		return new ResponseEntity<>(clientService.getClients(), new HttpHeaders(), HttpStatus.OK);

	}

	@PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ClientCommand> createClient(@Valid @RequestBody ClientCommand command)
			throws ClientNotFoundException {
		return new ResponseEntity<>(clientService.saveClientCommand(command), new HttpHeaders(), HttpStatus.OK);

	}

	@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ClientCommand> updateClient(@Valid @RequestBody ClientCommand command)
			throws ClientNotFoundException {
		ClientCommand newCommand = clientService.updateClientCommand(command);
		return new ResponseEntity<ClientCommand>(newCommand, new HttpHeaders(), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) throws ClientNotFoundException {

		Long clientId = Long.parseLong(id);
		clientService.deleteById(clientId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> findByid(@PathVariable String id) throws ClientNotFoundException {
		Long clientId = Long.parseLong(id);
		return new ResponseEntity<>(clientService.findById(clientId), new HttpHeaders(), HttpStatus.OK);

	}


}
