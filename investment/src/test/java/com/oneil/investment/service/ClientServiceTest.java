package com.oneil.investment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import com.oneil.investment.command.ClientCommand;
import com.oneil.investment.converters.ClientCommandToClient;
import com.oneil.investment.converters.ClientToClientCommand;
import com.oneil.investment.converters.FundCommandToFund;
import com.oneil.investment.converters.InvestorCommandToInvestor;
import com.oneil.investment.entity.Client;
import com.oneil.investment.entity.Fund;
import com.oneil.investment.entity.Investor;
import com.oneil.investment.repository.ClientRepository;
import com.oneil.investment.repository.FundRepository;
import com.oneil.investment.repository.InvestorRepository;
import org.springframework.transaction.annotation.Transactional;
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

	@Mock
	ClientRepository clientRepository;
	@Mock
	InvestorRepository investorRepository;
	@Mock
	FundRepository fundRepository;
	@Mock
    ClientToClientCommand clientToClientCommand;
	@InjectMocks
	ClientServiceImpl service;
	@Mock
	ClientCommandToClient clientCommandToClient;
	@Mock
	InvestorCommandToInvestor investorConverter;
	@Mock
	FundCommandToFund fundConverter;
	
	Client returnclient;

	@BeforeEach
	void setUp() {
		/*service=new ClientServiceImpl( clientRepository,  investorRepository,
				fundRepository,  clientCommandToClient,
				clientToClientCommand,  investorConverter,
				 fundConverter);*/
		returnclient = new Client("Exciting Investment", "Big investment company",  null);
		;
	}

	@DisplayName("Test Find All")
	@Test
	void findAll() {
		Client client = new Client();

		List<Client> clients = new ArrayList<>();
		clients.add(client);

		when(clientRepository.findAll()).thenReturn(clients);

		List<Client> foundClients = clientRepository.findAll();
			

		verify(clientRepository).findAll();

		assertThat(foundClients).hasSize(1);

	}
	
	

	@DisplayName("Test Count")
	@Test
	void count() throws InterruptedException, ExecutionException {
		when(clientRepository.count()).thenReturn(1L);
		Long cnt = service.count();
		assertEquals(1, cnt);
		verify(clientRepository).count();
	}

	@Test
	void findById() throws InterruptedException, ExecutionException {

		when(clientRepository.findById(anyLong())).thenReturn(Optional.of(returnclient));

		Client foundClient = service.findById(1L);

		verify(clientRepository).findById(anyLong());

		assertThat(foundClient).isNotNull();
		assertEquals("Exciting Investment", foundClient.getClientName());
		assertEquals("Big investment company", foundClient.getDescription());
	}

	@Test
    public void getInvestorByIdTest() throws Exception {
        Investor investor = new Investor();
        investor.setId(1L);
        Optional<Investor> clientOptional = Optional.of(investor);

        when(investorRepository.findById(anyLong())).thenReturn(clientOptional);

        Investor investorReturned = investorRepository.findById(1L).get();

        assertNotNull( investorReturned,"Null investor returned");
        verify(investorRepository, times(1)).findById(anyLong());
        verify(investorRepository, never()).findAll();
    }
	
	@Test
    public void getFundByIdTest() throws Exception {
        Fund fund = new Fund();
        fund.setId(1L);
        Optional<Fund> clientOptional = Optional.of(fund);

        when(fundRepository.findById(anyLong())).thenReturn(clientOptional);

        Fund fundReturned = fundRepository.findById(1L).get();

        assertNotNull( fundReturned,"Null fund returned");
        verify(fundRepository, times(1)).findById(anyLong());
        verify(fundRepository, never()).findAll();
    }
	
	@Test
    public void getClientCommandByIdTest() throws Exception {
        Client client = new Client();
        client.setId(1L);
        Optional<Client> clientOptional = Optional.of(client);

        when(clientRepository.findById(anyLong())).thenReturn(clientOptional);

        ClientCommand clientCommand = new ClientCommand();
        clientCommand.setId(1L);
        when(clientToClientCommand.convert(any())).thenReturn(clientCommand);
       
        ClientCommand commandById = service.findCommandById(1L);

        assertNotNull( commandById,"Null client returned");
        verify(clientRepository, times(1)).findById(anyLong());
        verify(clientRepository, never()).findAll();
    }
	

	

	@Test
	void deleteById() {

		service.deleteById(1L);

		verify(clientRepository).deleteById(anyLong());
	}

	@Test
	void deleteById2x() {
		service.deleteById(1l);
		service.deleteById(1l);

		verify(clientRepository, times(2)).deleteById(1l);
	}

	@Test
	void deleteByIdAtLeast() {
		service.deleteById(1l);
		service.deleteById(1l);

		verify(clientRepository, atLeastOnce()).deleteById(1l);
	}

	@Test
	void deleteByIdAtMost() {
		service.deleteById(1l);
		service.deleteById(1l);

		verify(clientRepository, atMost(5)).deleteById(1l);
	}

	@Test
	void deleteByIdNever() {
		service.deleteById(1l);
		service.deleteById(1l);

		verify(clientRepository, atLeastOnce()).deleteById(1l);

		verify(clientRepository, never()).deleteById(5L);
	}

	@Test
	void testDelete() {
	//	service.delete(new Client());
	}

	
}
