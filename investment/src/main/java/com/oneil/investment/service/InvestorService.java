package com.oneil.investment.service;


import java.util.List;
import java.util.Set;

import com.oneil.investment.command.InvestorCommand;
import com.oneil.investment.entity.Investor;

public interface InvestorService  {

    InvestorCommand findByClientIdAndInvestorId(Long clientId, Long investorId);

    InvestorCommand saveInvestorCommand(InvestorCommand command);

    Set<InvestorCommand> findInvestorsByClientId(Long client_id);
    void deleteById(Long recipeId, Long idToDelete);
    
  

}