package com.oneil.investment.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneil.investment.command.InvestorCommand;
import com.oneil.investment.entity.Client;
import com.oneil.investment.entity.Investor;
import com.oneil.investment.service.ClientService;
import com.oneil.investment.service.InvestorService;

@WebMvcTest(InvestorController.class)
class investorControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	ClientService clientService;
	@MockBean
	InvestorService investorService;
	InvestorCommand newInvestor=new InvestorCommand();

	
	Set<InvestorCommand> investorList = new HashSet<InvestorCommand>();
	String investJson = "";
	 @Autowired
	 ObjectMapper objectMapper;
	 Client client1=new Client();
	 Investor investor1 = new Investor("John Smith","jsmith@hper",client1,null);
	String investor1Json="";
	@BeforeEach
	void setUp() throws JsonProcessingException {
		newInvestor.setId(1l);
		newInvestor.setName("Joe Smoe");
		newInvestor.setEmail("js@gmail.com");
		newInvestor.setClientId(3l);
		investorList.add(newInvestor);
		investJson = objectMapper.writeValueAsString(investorList);
	
		
	}

	

	@Test
	void investorById() throws Exception {
		 
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/investors/investors/{id}" ,"1"))
				.andDo(MockMvcResultHandlers.log()).andReturn();
		   mockMvc.perform(get("/api/v1/investors/investors/{id}" ,"3")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(investJson))
	                .andExpect(status().isNoContent());
		   mockMvc.perform(get("/api/v1/investors/investors/{id}" ,"1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("[]"))
	                .andExpect(status().isNoContent());
		
	}
	
	
	
	
	

}
