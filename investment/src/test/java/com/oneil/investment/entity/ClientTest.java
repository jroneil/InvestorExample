package com.oneil.investment.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.oneil.investment.EntityTests;


@DataJpaTest
class ClientTest implements EntityTests {

	private Client client1;

	private Investor investor1;

	private Investor investor2;
	private Fund fund1;
	private Fund fund2;
	private Fund fund3;
	private Fund fund4;
	
	@BeforeEach
	public void setUp() {
		client1 = new Client("Harper Investments", "Big Investment company",null);
		client1.setId(1l);
		client1.setVersion(2l);
		investor1 = new Investor("Mar Zuckerber", "mark@mark.com",client1,null);
		investor1.setId(1l);
		investor2 = new Investor("Chri Hughe", "chris@chris.com",client1,null);
		investor2.setId(2l);
		Set<Investor> investorSet1 = new HashSet<Investor>();
		investorSet1.add(investor1);
		investorSet1.add(investor2);
		client1.setInvestors(investorSet1);
		fund1=new Fund("Stocks",investor1);
		fund1.setId(1l);
		fund2=new Fund("Stocks and Bonds",investor1);
		fund1.setId(2l);
		fund3=new Fund("Fixed Incom",investor2);
		fund1.setId(3l);
		fund4=new Fund("Stocks growth",investor2);
		fund1.setId(4l);
		Set<Fund> fundSet1 = new HashSet<Fund>();
		fundSet1.add(fund1);
		fundSet1.add(fund2);
		
		Set<Fund> fundSet2 = new HashSet<Fund>();
		fundSet2.add(fund3);
		fundSet2.add(fund4);
		investor1.setFunds(fundSet1);
		investor2.setFunds(fundSet2);
		
		
		

	}
	
	
	@Test
	void dependentAssertions() {
	

		assertAll("Properties Test",
				() -> assertAll("client Properties",
						() -> assertEquals("Harper Investments", client1.getClientName(), "Client Name did not Match"),
						() -> assertEquals("Big Investment company", client1.getDescription(),"Description did not Match")));

		
		List<String>investorList=new ArrayList<String>();
		List<String>fundsList=new ArrayList<String>();
		client1.getInvestors().forEach((investor)->{
			investorList.add(investor.getName());
			investorList.add(investor.getEmail());
			investor.getFunds().forEach((fund)->fundsList.add(fund.getName()));
		});
		assertEquals(true,investorList.contains("Mar Zuckerber"));
        assertEquals(true,investorList.contains("Chri Hughe"));
        assertEquals(true,investorList.contains("mark@mark.com"));
        assertEquals(true,investorList.contains("chris@chris.com"));
        assertEquals(true,fundsList.contains("Stocks"));
        assertEquals(true,fundsList.contains("Stocks and Bonds"));
        assertEquals(true,fundsList.contains("Fixed Incom"));
        assertEquals(true,fundsList.contains("Stocks growth"));

	}

	@Test
	void newObj() {
		
		Client client = new Client("Harper Investments", "Big Investment company",null);
		assertEquals(true, client.isNew());
		client.setId(1l);
		assertEquals(false, client.isNew());

	}
	 @Test
	    public void constructorTest(){
		 Set<Investor> investors=new HashSet<Investor>();
		 Client client = new Client("Harper Investments", "Big Investment company",investors);
		 assertEquals("Harper Investments", client.getClientName());
	    }
	     
	

	
}
