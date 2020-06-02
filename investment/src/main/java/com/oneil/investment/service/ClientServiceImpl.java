package com.oneil.investment.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneil.investment.command.ClientCommand;
import com.oneil.investment.command.InvestorCommand;
import com.oneil.investment.converters.ClientCommandToClient;
import com.oneil.investment.converters.ClientToClientCommand;
import com.oneil.investment.converters.FundCommandToFund;
import com.oneil.investment.converters.InvestorCommandToInvestor;
import com.oneil.investment.converters.InvestorToInvestorCommand;
import com.oneil.investment.entity.Client;
import com.oneil.investment.entity.Fund;
import com.oneil.investment.entity.Investor;
import com.oneil.investment.repository.ClientRepository;
import com.oneil.investment.repository.InvestorRepository;
import com.oneil.investment.repository.FundRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private final ClientRepository clientRepository;
	private final InvestorRepository investorRepository;
	private final FundRepository fundRepository;
	private final ClientCommandToClient clientCommandToClient;
	private final ClientToClientCommand clientToClientCommand;
	private final InvestorCommandToInvestor investorConverter;
	private final InvestorToInvestorCommand investorToInvestorCommand;
	private final FundCommandToFund fundConverter;
	


	public ClientServiceImpl(ClientRepository clientRepository, InvestorRepository investorRepository,
			FundRepository fundRepository, ClientCommandToClient clientCommandToClient,
			ClientToClientCommand clientToClientCommand, InvestorCommandToInvestor investorConverter,
			InvestorToInvestorCommand investorToInvestorCommand, FundCommandToFund fundConverter) {
		super();
		this.clientRepository = clientRepository;
		this.investorRepository = investorRepository;
		this.fundRepository = fundRepository;
		this.clientCommandToClient = clientCommandToClient;
		this.clientToClientCommand = clientToClientCommand;
		this.investorConverter = investorConverter;
		this.investorToInvestorCommand = investorToInvestorCommand;
		this.fundConverter = fundConverter;
	}

	public Long count() {

		return clientRepository.count();
	}

	@Override
	@Transactional
	public Set<Client> getClients() {
		log.info("I am herer!!!!getClients!!");
		Set<Client> clientSet = new HashSet<>();

		log.info("----------------------1");
		try {
			clientRepository.findAll().iterator().forEachRemaining(clientSet::add);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		log.info("----------------------2");
		return clientSet;

	}

	public Client findById(Long l) {
		Optional<Client> clientOptional = clientRepository.findById(l);
		if (!clientOptional.isPresent()) {
			throw new RuntimeException("Client Not Found!");
		}

		return clientOptional.get();
	}

	public InvestorCommand findInvestorCommandById(Long l) {
		Optional<Investor> investorOptional = investorRepository.findById(l);
		if (!investorOptional.isPresent()) {
			throw new RuntimeException("Client Not Found!");
		}
		
		return investorToInvestorCommand.convert(investorOptional.get());
	}

	public Investor findInvestorById(Long l) {
		Optional<Investor> investorOptional = investorRepository.findById(l);
		if (!investorOptional.isPresent()) {
			throw new RuntimeException("Client Not Found!");
		}

		return investorOptional.get();
	}
	public Fund findFundById(Long l) {
		Optional<Fund> fundOptional = fundRepository.findById(l);
		if (!fundOptional.isPresent()) {
			throw new RuntimeException("Client Not Found!");
		}

		return fundOptional.get();
	}

	@Override
	@Transactional
	public ClientCommand findCommandById(Long l) {
		return clientToClientCommand.convert(findById(l));
	}

	@Override
	@Transactional
	public ClientCommand saveClientCommand(ClientCommand command) {
		log.info("------------------0");
		Client detachedClient = clientCommandToClient.convert(command);
		log.info("------------------1 detachedClient.getId()=" + detachedClient.getId());
		Client savedClient = clientRepository.save(detachedClient);

		log.info("------------------2savedClient.getId() " + savedClient.getId());
		command.getInvestors().forEach((investor) -> {

			Investor inv = investorConverter.convert(investor);
			inv.setClient(savedClient);
			Investor savedInvestor = investorRepository.save(inv);
			investor.getFunds().forEach((fund) -> {
				Fund tFund = fundConverter.convert(fund);
				tFund.setInvestor(savedInvestor);
				fundRepository.save(tFund);

			});
		});
		log.debug("Saved ClientId:" + savedClient.getId());
		return clientToClientCommand.convert(savedClient);
	}

	@Override
	@Transactional
	public ClientCommand updateClientCommand(ClientCommand command) {
		Client clientEntity = findById(command.getId());
		clientEntity.setClientName(command.getClientName());
		clientEntity.setDescription(command.getDescription());
		if (command.getInvestors().size() == 0) {
			clientEntity.setInvestors(new HashSet<>());
		}
		Client savedClient = clientRepository.save(clientEntity);
		command.getInvestors().forEach((investor) -> {
			if (investor.getId() == null) {
				Investor inv = investorConverter.convert(investor);
				inv.setClient(savedClient);
				Investor savedInvestor = investorRepository.save(inv);
				investor.getFunds().forEach((fund) -> {
					Fund tFund = fundConverter.convert(fund);
					tFund.setInvestor(savedInvestor);
					fundRepository.save(tFund);

				});
			} else {
				Investor InvestorEntity = findInvestorById(investor.getId());
				InvestorEntity.setName(investor.getName());
				InvestorEntity.setEmail(investor.getEmail());
				Investor savedInvestor = investorRepository.save(InvestorEntity);

				investor.getFunds().forEach((fund) -> {
					if (investor.getId() == null) {
						Fund tFund = fundConverter.convert(fund);
						tFund.setInvestor(savedInvestor);
						fundRepository.save(tFund);
					} else {
						Fund FundEntity = findFundById(fund.getId());
						FundEntity.setName(fund.getName());
						fundRepository.save(FundEntity);
					}
				});
			}
		});
		return clientToClientCommand.convert(savedClient);
	}

	

	@Override
	public void deleteById(Long idToDelete) {
		clientRepository.deleteById(idToDelete);
	}

}
