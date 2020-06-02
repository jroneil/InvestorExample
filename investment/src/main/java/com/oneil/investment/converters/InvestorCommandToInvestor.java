package com.oneil.investment.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.oneil.investment.command.InvestorCommand;
import com.oneil.investment.entity.Investor;

import lombok.Synchronized;

@Component
public class InvestorCommandToInvestor implements Converter<InvestorCommand, Investor> {

	

	@Synchronized
	@Nullable
	@Override
	public Investor convert(InvestorCommand source) {
		if (source == null) {
			return null;
		}
		final Investor investor = new Investor();
		investor.setId(source.getId());
		investor.setName(source.getName());
		investor.setEmail(source.getEmail());

	
		return investor;
	}
}
