package com.iBase.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.iBase.domain.UserInfo;

public class ImageLoaderTest {

	private ImageLoader imgLoader;
	private UserInfo user;
	
	@Before 
    public void initialize() throws Exception {
		user = new UserInfo();
		user.setFirstName("fName");
		user.setFriendList("friendsList");
		user.setImageCount(0);
		user.setImagesList("[{\"imageLocation\":\"/Users/KartheekGanesh/Sources/iBaseTestImages/1.jpg\""
						+ ",\"imageId\":\"1\",\"imageTitle\":\"testImage1\",\"likes\":0}"
						+ ",{\"imageLocation\":\"/Users/KartheekGanesh/Sources/iBaseTestImages/2.jpg\""
						+ ",\"imageId\":\"2\",\"imageTitle\":\"testImage2\",\"likes\":0}]");
		user.setLastName("lName");
		user.setPassword("pass");
		user.setUserId("uId");
		imgLoader = new ImageLoader(user);
	}
	
	
	@Test
	public void testGetImageLocations(){
//		List<String> images = imgLoader.getImageLocations();
//		List<String> checkimages = new ArrayList<String>();
//		checkimages.add("/Users/KartheekGanesh/Sources/iBaseTestImages/1.jpg");
//		checkimages.add("/Users/KartheekGanesh/Sources/iBaseTestImages/2.jpg");
//		assertEquals(checkimages, images);
		
	}
}
