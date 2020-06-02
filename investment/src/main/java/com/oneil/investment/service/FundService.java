package com.oneil.investment.service;


import com.oneil.investment.command.FundCommand;

public interface FundService  {


	FundCommand findByInvestorIdAndFundId(Long investorId, Long fundId);

	FundCommand saveFundCommand(FundCommand command);

	void deleteById(Long investorId, Long idToDelete);

}