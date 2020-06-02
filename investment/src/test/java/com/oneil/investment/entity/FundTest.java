package com.oneil.investment.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

class FundTest {

	

	 @Test
	 void newObj() {
		 Fund fund=new Fund();
		 assertEquals(true, fund.isNew());
		 fund.setId(1l);
		 assertEquals(false, fund.isNew());
		 
	 }
	 
	 @Test
	    public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {
	        //given
	        final Fund pojo = new Fund();

	        //when
	        pojo.setName("foo");
	     
	        //then
	        final Field field = pojo.getClass().getDeclaredField("name");
	        field.setAccessible(true);
	        assertEquals( "foo",field.get(pojo), "Name Fields didn't match");
	      
	    }
}
