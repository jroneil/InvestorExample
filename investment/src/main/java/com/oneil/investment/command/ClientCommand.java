package com.oneil.investment.command;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
public class ClientCommand {
	private Long id;
	private String clientName;
	private String description;
	private Set<InvestorCommand> investors= new HashSet<>();
}
