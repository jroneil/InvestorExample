package com.oneil.investment.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.oneil.investment.command.FundCommand;
import com.oneil.investment.entity.Fund;

import lombok.Synchronized;
@Component
public class FundCommandToFund implements Converter<FundCommand, Fund> {

	
	@Synchronized
	@Nullable
	@Override
	public Fund convert(FundCommand source) {
		  if (source == null) {
	            return null;
	        }

	        final Fund fund = new Fund();
	        fund.setId(source.getId());
	        fund.setName(source.getName());
	  
	      

	        return fund;
	    }
}
