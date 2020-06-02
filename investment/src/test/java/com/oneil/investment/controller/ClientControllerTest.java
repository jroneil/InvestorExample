package com.oneil.investment.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneil.investment.command.ClientCommand;
import com.oneil.investment.command.InvestorCommand;
import com.oneil.investment.entity.Client;
import com.oneil.investment.entity.Investor;
import com.oneil.investment.service.ClientService;

@WebMvcTest(ClientController.class)
class clientControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	ClientService clientService;

	ClientCommand newclient1 = new 	ClientCommand();
	InvestorCommand newInvestor=new InvestorCommand();

	String client1Json = "";
	Set<InvestorCommand> investorList = new HashSet<InvestorCommand>();
	String investJson = "";

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws JsonProcessingException {
		newclient1.setId(1l);
		newclient1.setClientName("Fidelity Investment");
		newclient1.setDescription("Boston Area Investment");
		client1Json = objectMapper.writeValueAsString(newclient1);
		newInvestor.setId(1l);
		newInvestor.setName("Joe Smoe");
		newInvestor.setEmail("js@gmail.com");
		newInvestor.setClientId(1l);
		investorList.add(newInvestor);
		investJson = objectMapper.writeValueAsString(investorList);
		
	}

	@Test
	void allclients() throws Exception {
		Set<ClientCommand>clientList = new HashSet<ClientCommand>();
		ClientCommand cmd1 = new 	ClientCommand();
		cmd1.setId(1l);
		cmd1.setClientName("Fidelity Investment");
		cmd1.setDescription("Boston Area Investment");
		ClientCommand cmd2 = new 	ClientCommand();
		cmd2.setId(2l);
		cmd2.setClientName("Liberty Mutual");
		cmd2.setDescription("Mutual fund company");
		clientList.add(cmd1);
				clientList.add(cmd2);
				
		String arrayToJson = objectMapper.writeValueAsString(clientList);
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/clients/all"))
				.andDo(MockMvcResultHandlers.log()).andReturn();
		mockMvc.perform(get("/api/v1/clients/all" ).accept(MediaType.APPLICATION_JSON)
		        .content(arrayToJson))
	         .andExpect(status().isOk());
		 

	}

	


	
	@Test
	void save() throws Exception {
		 
		MvcResult mvcResult = mockMvc.perform(post("/api/v1/clients/save"))
				.andDo(MockMvcResultHandlers.log()).andReturn();
		   mockMvc.perform(post("/api/v1/clients/save")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(client1Json))
	                .andExpect(status().isOk());
		
	}
	@Test
	void update() throws Exception {
		 
		MvcResult mvcResult = mockMvc.perform(put("/api/v1/clients/update"))
				.andDo(MockMvcResultHandlers.log()).andReturn();
		   mockMvc.perform(put("/api/v1/clients/update")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(client1Json))
	                .andExpect(status().isOk());
		
	}
	
	

	@Test
	void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/{id}", "11")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void findbyId() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/clients/{id}", "1"))
				.andDo(MockMvcResultHandlers.log()).andReturn();
		 mockMvc.perform(get("/api/v1/clients/{id}", "1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(client1Json))
	                .andExpect(status().isOk());
	}
/*work on later if I have time
	@Test
	void findInvestorsByClientid() throws Exception {
		System.out.println("xxxxx"+investJson);
			mockMvc.perform(
				get("/api/v1/clients/investors/{id}", 5l).accept(MediaType.APPLICATION_JSON).content(investJson))
				.andExpect(status().isOk());
	}
*/
	
}
