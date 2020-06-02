package com.oneil.investment.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.oneil.investment.command.InvestorCommand;
import com.oneil.investment.entity.Investor;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class InvestorToInvestorCommand implements Converter<Investor,InvestorCommand> {
	private final FundToFundCommand fundConverter;

	public InvestorToInvestorCommand(FundToFundCommand fundConverter) {
		super();
		this.fundConverter = fundConverter;
	}

	public InvestorToInvestorCommand() {
		this.fundConverter = new FundToFundCommand();
		
	}

	@Synchronized
	@Nullable
	@Override
	public InvestorCommand convert(Investor source) {
		if (source == null) {
			return null;
		}

		final InvestorCommand investorCommand = new InvestorCommand();
		investorCommand.setId(source.getId());
		investorCommand.setName(source.getName());
		investorCommand.setEmail(source.getEmail());
		if(source.getClient()!=null) {
		investorCommand.setClientId(source.getClient().getId());
		}
		
		
	
		if (source.getFunds() != null && source.getFunds().size() > 0) {
			source.getFunds().forEach(fund -> investorCommand.getFunds().add(fundConverter.convert(fund)));
		}

		return investorCommand;
	}
}
