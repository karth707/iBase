package com.iBase.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserInfoTest {

	private UserInfo userInfo;
	
	@Before 
    public void initialize() throws Exception {
		userInfo = new UserInfo();
	}
	
	@Test
	public void testSetAndGetUserId(){
		String userId = "abc@asu.edu";
		Assert.assertNull(userInfo.getUserId());
		userInfo.setUserId(userId);
		Assert.assertEquals(userId, userInfo.getUserId());
	}
	
	@Test
	public void testSetAndGetPassword(){
		String password = "abcd";
		Assert.assertNull(userInfo.getPassword());
		userInfo.setPassword(password);
		Assert.assertEquals(password, userInfo.getPassword());
	}
	
	@Test
	public void testSetAndGetFriendsList(){
		String friendsList = "{[\"a@c.com\", \"d@e.com\"]}";
		Assert.assertNull(userInfo.getFriendList());
		userInfo.setFriendList(friendsList);
		Assert.assertEquals(friendsList, userInfo.getFriendList());
	}
	
	@Test
	public void testSetAndGetImagesList(){
		String imagesList = "{[\"a/b/c\", \"d/e\"]}";
		Assert.assertNull(userInfo.getImagesList());
		userInfo.setImagesList(imagesList);
		Assert.assertEquals(imagesList, userInfo.getImagesList());
	}
	
	@Test
	public void testSetAndGetInfo(){
		String fName = "kartheek";
		String lname = "Ganesh";
		String profilePic = "/a/b/c";
		Assert.assertNull(userInfo.getFirstName());
		Assert.assertNull(userInfo.getLastName());
		Assert.assertNull(userInfo.getProfilePic());
		userInfo.setFirstName(fName);
		userInfo.setLastName(lname);
		userInfo.setProfilePic(profilePic);
		Assert.assertEquals(fName, userInfo.getFirstName());
		Assert.assertEquals(lname, userInfo.getLastName());
		Assert.assertEquals(profilePic, userInfo.getProfilePic());
	}
	
}
