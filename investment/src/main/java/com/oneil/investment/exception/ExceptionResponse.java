package com.oneil.investment.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {
	private Date timestamp;
	private String Message;
	private String detail;

}
