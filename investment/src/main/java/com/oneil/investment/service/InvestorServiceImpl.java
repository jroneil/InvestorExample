package com.oneil.investment.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneil.investment.command.InvestorCommand;
import com.oneil.investment.converters.InvestorCommandToInvestor;
import com.oneil.investment.converters.InvestorToInvestorCommand;
import com.oneil.investment.entity.Client;
import com.oneil.investment.entity.Investor;
import com.oneil.investment.repository.ClientRepository;
import com.oneil.investment.repository.InvestorRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class InvestorServiceImpl implements InvestorService {
	private final ClientRepository clientRepository;
	private final InvestorCommandToInvestor investorCommandToInvestor;
	private final InvestorToInvestorCommand investorToInvestorCommand;
	private final InvestorRepository investorRepository;

	
	



	public InvestorServiceImpl(ClientRepository clientRepository, InvestorCommandToInvestor investorCommandToInvestor,
			InvestorToInvestorCommand investorToInvestorCommand, InvestorRepository investorRepository) {
		super();
		this.clientRepository = clientRepository;
		this.investorCommandToInvestor = investorCommandToInvestor;
		this.investorToInvestorCommand = investorToInvestorCommand;
		this.investorRepository = investorRepository;
	}



	@Override
	public InvestorCommand findByClientIdAndInvestorId(Long clientId, Long investorId) {
		   Optional<Client> clientOptional = clientRepository.findById(clientId);

	        if (!clientOptional.isPresent()){
	            //todo impl error handling
	            log.error("client id not found. Id: " + clientId);
	        }

	        Client client = clientOptional.get();

	        Optional<InvestorCommand> investorCommandOptional = client.getInvestors().stream()
	                .filter(investor -> investor.getId().equals(investorId))
	                .map( investor -> investorToInvestorCommand.convert(investor)).findFirst();

	        if(!investorCommandOptional.isPresent()){
	            //todo impl error handling
	            log.error("Investor id not found: " + investorId);
	        }

	        return investorCommandOptional.get();
	}



	   @Override
	    @Transactional
	public InvestorCommand saveInvestorCommand(InvestorCommand command) {
		   Optional<Client> clientOptional = clientRepository.findById(command.getClientId());

	        if(!clientOptional.isPresent()){

	            //todo toss error if not found!
	            log.error("Client not found for id: " + command.getClientId());
	            return new InvestorCommand();
	        } else {
	            Client client = clientOptional.get();

	            Optional<Investor> investorOptional = client
	                    .getInvestors()
	                    .stream()
	                    .filter(investor -> investor.getId().equals(command.getId()))
	                    .findFirst();

	            if(investorOptional.isPresent()){
	                Investor investorFound = investorOptional.get();
	                investorFound.setName(command.getName());
	                investorFound.setEmail(command.getEmail());
	                
	              
	            } else {
	                //add new Investor
	                Investor investor = investorCommandToInvestor.convert(command);
	                investor.setClient(client);
	                client.addInvestor(investor);
	            }

	            Client savedClient = clientRepository.save(client);

	            Optional<Investor> savedInvestorOptional = savedClient.getInvestors().stream()
	                    .filter(clientInvestors -> clientInvestors.getId().equals(command.getId()))
	                    .findFirst();

	            //check by description
	            if(!savedInvestorOptional.isPresent()){
	                //not totally safe... But best guess
	                savedInvestorOptional = savedClient.getInvestors().stream()
	                        .filter(clientInvestors -> clientInvestors.getName().equals(command.getName()))
	                        .filter(clientInvestors -> clientInvestors.getEmail().equals(command.getEmail()))
	                          .findFirst();
	            }

	            //to do check for fail
	            return investorToInvestorCommand.convert(savedInvestorOptional.get());
	        }

	    }

	    @Override
	    public void deleteById(Long clientId, Long idToDelete) {

	        log.debug("Deleting investor: " + clientId + ":" + idToDelete);

	        Optional<Client> clientOptional = clientRepository.findById(clientId);

	        if(clientOptional.isPresent()){
	            Client client = clientOptional.get();
	            log.debug("found client");

	            Optional<Investor> investorOptional = client
	                    .getInvestors()
	                    .stream()
	                    .filter(investor -> investor.getId().equals(idToDelete))
	                    .findFirst();

	            if(investorOptional.isPresent()){
	                log.debug("found Investor");
	                Investor investorToDelete = investorOptional.get();
	                investorToDelete.setClient(null);
	                client.getInvestors().remove(investorOptional.get());
	                clientRepository.save(client);
	            }
	        } else {
	            log.debug("Client Id Not found. Id:" + clientId);
	        }
	    }



		@Override
		public Set<InvestorCommand> findInvestorsByClientId(Long client_id) {
			List<Investor> list=investorRepository.findInvestorsByClientId(client_id);
			Set<InvestorCommand>investorSet=new HashSet<InvestorCommand>();
			if(list.size()>0) {
			list.forEach(investor->investorSet.add(investorToInvestorCommand.convert(investor)));
			}
			return investorSet;
		}
	}