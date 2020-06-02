package com.oneil.investment.controller;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oneil.investment.command.ClientCommand;
import com.oneil.investment.command.InvestorCommand;
import com.oneil.investment.entity.Fund;
import com.oneil.investment.entity.Investor;
import com.oneil.investment.exception.InvestorNotFoundException;
import com.oneil.investment.service.InvestorService;

import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/investors")
public class InvestorController {
	private final InvestorService investorService;

	@Autowired
	public InvestorController(InvestorService investorService) {
		super();
		this.investorService = investorService;
	}

	@GetMapping("/investors/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Set<InvestorCommand>> findInvestorsByClientid(@PathVariable String id)
			throws InvestorNotFoundException, InterruptedException, ExecutionException {
		Long clientId = Long.parseLong(id);
		Set<InvestorCommand> investorList = investorService.findInvestorsByClientId(clientId);
		if(investorList.size()==0) {
			return new ResponseEntity<>(investorList, new HttpHeaders(), HttpStatus.NO_CONTENT);
		}
			return new ResponseEntity<>(investorList, new HttpHeaders(), HttpStatus.OK);
		
	}

}
