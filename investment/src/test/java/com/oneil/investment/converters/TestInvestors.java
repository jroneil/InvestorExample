package com.oneil.investment.converters;

import java.util.HashSet;
import java.util.Set;

import com.oneil.investment.entity.Client;
import com.oneil.investment.entity.Fund;
import com.oneil.investment.entity.Investor;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TestInvestors {

	public static void main(String[] args) {
		Client client=new Client();
    	client.setClientName("clientName");
    	client.setDescription("description");
    	client.setId(1l);
		 Investor investor = new Investor();
         investor.setId(2l);
         investor.setName("name1");
         investor.setEmail("email1@g.com");
         Investor investor2 = new Investor();
         investor2.setId(4l);
         investor2.setName("name2");
         investor2.setEmail("email@g.com");

         client.getInvestors().add(investor);
         client.getInvestors().add(investor2);
         Set<String> setA = new HashSet<>();

         setA.add("element 1");
         setA.add("element 2");
         setA.add("element 3");
         log.info(" setA.size()"+  setA.size());
      
        log.info("  client.getInvestors().size()"+   client.getInvestors().size());

	}

}
