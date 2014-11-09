package com.iBase.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NewUserTest {

	private NewUser user;
	
	@Before 
    public void initialize() throws Exception {
		user = new NewUser();
	}
	
	@Test
	public void testSetAndGetFirstname(){
		String fname = "first";
		Assert.assertNull(user.getFirstName());
		user.setFirstName(fname);
		Assert.assertEquals(fname, user.getFirstName());
	}
	
	@Test
	public void testSetAndGetLastname(){
		String lname = "last";
		Assert.assertNull(user.getLastName());
		user.setLastName(lname);
		Assert.assertEquals(lname, user.getLastName());
	}
	
	@Test
	public void testSetAndGetEmail(){
		String email = "email";
		Assert.assertNull(user.getEmail());
		user.setEmail(email);
		Assert.assertEquals(email, user.getEmail());
	}
	
	@Test
	public void testSetAndGetPassword(){
		String pass = "pass";
		Assert.assertNull(user.getPassword());
		user.setPassword(pass);
		Assert.assertEquals(pass, user.getPassword());
	}
	
	
}
