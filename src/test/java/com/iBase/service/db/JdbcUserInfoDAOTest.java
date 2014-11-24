package com.iBase.service.db;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iBase.domain.UserInfo;

public class JdbcUserInfoDAOTest {

	@Autowired
	private UserInfoDAO userInfoDAO;
	
	private UserInfo userInfo;
	
	
	@Before 
    public void initialize() throws Exception {
		
		userInfo = new UserInfo();
		userInfo.setUserId("kage@asu.edu");
		userInfo.setFirstName("Kartheek");
		userInfo.setLastName("Ganesh");
		userInfo.setPassword("password");
		userInfo.setFriendList("[\"friend1\", \"friend2\", \"friend3\"]");
		userInfo.setImageCount(2);
		userInfo.setImagesList("[{\"imageLocation\":\"/Users/KartheekGanesh/Sources/iBaseTestImages/1.jpg\""
						+ ",\"imageId\":\"1\",\"imageTitle\":\"testImage1\",\"likes\":0}"
						+ ",{\"imageLocation\":\"/Users/KartheekGanesh/Sources/iBaseTestImages/2.jpg\""
						+ ",\"imageId\":\"2\",\"imageTitle\":\"testImage2\",\"likes\":1}]");
		userInfo.setProfilePic("/a/b/c.jpg");
	}
	
	@Test
	public void testInsert(){
		
		//Assert.assertEquals(true, userInfoDAO.insert(userInfo));
	}
	
	@Test
	public void testInsertUserRole(){
		
	}
	
	@Test
	public void testUpdateTable(){
		
		
	}
	
	@Test
	public void testUpdateProfilePicture(){
		
	}
	
	@Test
	public void testFindById(){
		
	}
}

