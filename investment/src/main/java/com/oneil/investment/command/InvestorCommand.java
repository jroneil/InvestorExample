package com.oneil.investment.command;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter   
@Setter
@NoArgsConstructor
public class InvestorCommand {
	private Long id;
	private Long clientId;
	private String name;
	private String email;
	private Set<FundCommand> funds=new HashSet<>();

}
