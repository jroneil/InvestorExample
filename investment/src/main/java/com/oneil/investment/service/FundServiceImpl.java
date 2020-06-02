package com.oneil.investment.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.oneil.investment.command.FundCommand;
import com.oneil.investment.command.FundCommand;
import com.oneil.investment.converters.FundCommandToFund;
import com.oneil.investment.converters.FundToFundCommand;
import com.oneil.investment.entity.Investor;
import com.oneil.investment.entity.Fund;
import com.oneil.investment.repository.InvestorRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class FundServiceImpl implements FundService {
	private final InvestorRepository investorRepository;
	private final FundCommandToFund fundCommandToFund;
	private final FundToFundCommand fundToFundCommand;
	
	
	public FundServiceImpl(InvestorRepository investorRepository, FundCommandToFund fundCommandToFund,
			FundToFundCommand fundToFundCommand) {
		super();
		this.investorRepository = investorRepository;
		this.fundCommandToFund = fundCommandToFund;
		this.fundToFundCommand = fundToFundCommand;
	}



	@Override
	public FundCommand findByInvestorIdAndFundId(Long investorId, Long fundId
			) {
		   Optional<Investor> investorOptional = investorRepository.findById(investorId);

	        if (!investorOptional.isPresent()){
	            //todo impl error handling
	            log.error("investor id not found. Id: " + investorId);
	        }

	        Investor investor = investorOptional.get();

	        Optional<FundCommand> fundCommandOptional = investor.getFunds().stream()
	                .filter(fund -> fund.getId().equals(fundId))
	                .map( fund -> fundToFundCommand.convert(fund)).findFirst();

	        if(!fundCommandOptional.isPresent()){
	            //todo impl error handling
	            log.error("Fund id not found: " + fundId);
	        }

	        return fundCommandOptional.get();
	}



	   @Override
	    @Transactional
	public FundCommand saveFundCommand(FundCommand command) {
		   Optional<Investor> investorOptional = investorRepository.findById(command.getInvestorId());

	        if(!investorOptional.isPresent()){

	            //todo toss error if not found!
	            log.error("Investor not found for id: " + command.getInvestorId());
	            return new FundCommand();
	        } else {
	            Investor investor = investorOptional.get();

	            Optional<Fund> fundOptional = investor
	                    .getFunds()
	                    .stream()
	                    .filter(fund -> fund.getId().equals(command.getId()))
	                    .findFirst();

	            if(fundOptional.isPresent()){
	                Fund fundFound = fundOptional.get();
	                fundFound.setName(command.getName());
	            
	                
	              
	            } else {
	                //add new Fund
	                Fund fund = fundCommandToFund.convert(command);
	                fund.setInvestor(investor);
	                investor.addFund(fund);
	            }

	            Investor savedInvestor = investorRepository.save(investor);

	            Optional<Fund> savedFundOptional = savedInvestor.getFunds().stream()
	                    .filter(investorFunds -> investorFunds.getId().equals(command.getId()))
	                    .findFirst();

	            //check by description
	            if(!savedFundOptional.isPresent()){
	                //not totally safe... But best guess
	                savedFundOptional = savedInvestor.getFunds().stream()
	                        .filter(investorFunds -> investorFunds.getName().equals(command.getName()))
	                           .findFirst();
	            }

	            //to do check for fail
	            return fundToFundCommand.convert(savedFundOptional.get());
	        }

	    }

	    @Override
	    public void deleteById(Long investorId, Long idToDelete) {

	        log.debug("Deleting fund: " + investorId + ":" + idToDelete);

	        Optional<Investor> investorOptional = investorRepository.findById(investorId);

	        if(investorOptional.isPresent()){
	            Investor investor = investorOptional.get();
	            log.debug("found investor");

	            Optional<Fund> fundOptional = investor
	                    .getFunds()
	                    .stream()
	                    .filter(fund -> fund.getId().equals(idToDelete))
	                    .findFirst();

	            if(fundOptional.isPresent()){
	                log.debug("found Fund");
	                Fund fundToDelete = fundOptional.get();
	                fundToDelete.setInvestor(null);
	                investor.getFunds().remove(fundOptional.get());
	                investorRepository.save(investor);
	            }
	        } else {
	            log.debug("Investor Id Not found. Id:" + investorId);
	        }
	    }
	}