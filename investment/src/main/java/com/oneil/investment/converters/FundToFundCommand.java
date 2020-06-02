package com.oneil.investment.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.oneil.investment.command.FundCommand;
import com.oneil.investment.entity.Fund;
@Component
public class FundToFundCommand implements Converter<Fund,FundCommand> {

	@Override
	public FundCommand convert(Fund source) {
		  if (source == null) {
	            return null;
	        }

	        final FundCommand fundCommand = new FundCommand();
	        fundCommand.setId(source.getId());
	        fundCommand.setName(source.getName());
	        if(source.getInvestor()!=null) {
	        fundCommand.setInvestorId(source.getInvestor().getId());
	        }
	      

	        return fundCommand;
	    }
	}

