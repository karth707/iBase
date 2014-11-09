package com.iBase.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class UserRoleTest {

	private UserRole userRole;
	
	@Before 
    public void initialize() throws Exception {
		userRole = new UserRole();
	}
	
	@Test
	public void testSetAndGetuserId(){
		String userId = "id";
		assertNull(userRole.getUserId());
		userRole.setUserId(userId);
		assertEquals(userId, userRole.getUserId());
	}
	
	@Test
	public void testSetAndGetRole(){
		String role = "role";
		assertNull(userRole.getRole());
		userRole.setRole(role);
		assertEquals(role, userRole.getRole());
	}
}
