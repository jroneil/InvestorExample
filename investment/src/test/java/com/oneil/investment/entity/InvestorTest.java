package com.oneil.investment.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvestorTest {

	private Client client1;

	private Investor investor1;

	private Investor investor2;
	private Fund fund1;
	private Fund fund2;
	private Fund fund3;
	private Fund fund4;
	
	 @BeforeEach
	    void setUp() {
			client1 = new Client("Harper Investments", "Big Investment company");
			client1.setId(1l);
			client1.setVersion(2l);
			investor1 = new Investor("Mar Zuckerber", "mark@mark.com",client1,null);
			investor2 = new Investor("Chri Hughe", "chris@chris.com",client1,null);
			Set<Investor> investorSet1 = new HashSet<Investor>();
			
			
			fund1=new Fund("Stocks",investor1);
			fund1.setId(1l);
			fund2=new Fund("Stocks and Bonds",investor1);
			fund3=new Fund("Fixed Incom",investor2);
			fund4=new Fund("Stocks growth",investor2);
			Set<Fund> fundSet1 = new HashSet<Fund>();
			fundSet1.add(fund1);
			fundSet1.add(fund2);
			
			Set<Fund> fundSet2 = new HashSet<Fund>();
			fundSet2.add(fund3);
			fundSet2.add(fund4);
			investor1.setFunds(fundSet1);
			investor2.setFunds(fundSet2);
			investorSet1.add(investor1);
			investorSet1.add(investor2);
			client1.setInvestors(investorSet1);
	    }
	

	 @Test
	 void newObj() {
		 Investor investor=new Investor();
		 assertEquals(true, investor.isNew());
		 investor.setId(1l);
		 assertEquals(false, investor.isNew());
		 
	 }
	 
	 @Test
	    public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {
	        //given
	        final Fund fund = new Fund();
	        fund.setName("Stock");
	        Set<Fund>fundSet=new HashSet<Fund>();
	        fundSet.add(fund);
	        //when
	        final Investor pojo= new Investor();
	        pojo.setName("John Smith");
	        pojo.setEmail("js@fargo.com");
	        pojo.setFunds(fundSet);
	     

	        //then
	        final Field field = pojo.getClass().getDeclaredField("name");
	        field.setAccessible(true);
	        assertEquals( "John Smith",field.get(pojo), "Name Fields didn't match");
	        assertEquals( 1,pojo.getFunds().size());
	        
	    }
}
