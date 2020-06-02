package com.oneil.investment.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FundCommand {
	private Long id;
	private Long investorId;
	private String name;

}
