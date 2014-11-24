package com.iBase.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iBase.domain.IBaseImage;
import com.iBase.domain.UserInfo;

public class ImageLoaderTest {

	private ImageLoader imgLoader, imgLoader2;
	private UserInfo user, user2;
	
	@Before 
    public void initialize() throws Exception {
		user = new UserInfo();
		user.setFirstName("fName");
		user.setFriendList("friendsList");
		user.setImageCount(0);
		user.setImagesList("[{\"imageLocation\":\"/Users/KartheekGanesh/Sources/iBaseTestImages/1.jpg\""
						+ ",\"imageId\":\"1\",\"imageTitle\":\"testImage1\",\"likes\":0}"
						+ ",{\"imageLocation\":\"/Users/KartheekGanesh/Sources/iBaseTestImages/2.jpg\""
						+ ",\"imageId\":\"2\",\"imageTitle\":\"testImage2\",\"likes\":1}]");
		user.setLastName("lName");
		user.setPassword("pass");
		user.setUserId("uId");
		imgLoader = new ImageLoader(user);
		
		user2 = new UserInfo();
		//user2.setImagesList(""); do not set images List to check if it returns null
		imgLoader2 = new ImageLoader(user2);
	}
	
	
	@Test
	public void testGetImageObjects(){
	
		List<IBaseImage> imageObjects = new ArrayList<IBaseImage>();

		//The two expected image Objects
		IBaseImage image1 = new IBaseImage();
		image1.setImageId("1");
		image1.setImageLocation("/Users/KartheekGanesh/Sources/iBaseTestImages/1.jpg");
		image1.setImageTitle("testImage1");
		image1.setLikes(0);
		IBaseImage image2 = new IBaseImage();
		image2.setImageId("2");
		image2.setImageLocation("/Users/KartheekGanesh/Sources/iBaseTestImages/2.jpg");
		image2.setImageTitle("testImage2");
		image2.setLikes(1);
		
		imageObjects.add(image1);
		imageObjects.add(image2);
		
		List<IBaseImage> imageObjectsActual = imgLoader.getImageObjects();
		
		Assert.assertEquals(imageObjects.get(0).getImageId(), imageObjectsActual.get(0).getImageId());
		Assert.assertEquals(imageObjects.get(0).getImageLocation(), imageObjectsActual.get(0).getImageLocation());
		Assert.assertEquals(imageObjects.get(0).getImageTitle(), imageObjectsActual.get(0).getImageTitle());
		Assert.assertEquals(imageObjects.get(0).getLikes(), imageObjectsActual.get(0).getLikes());
		Assert.assertEquals(imageObjects.get(1).getImageId(), imageObjectsActual.get(1).getImageId());
		Assert.assertEquals(imageObjects.get(1).getImageLocation(), imageObjectsActual.get(1).getImageLocation());
		Assert.assertEquals(imageObjects.get(1).getImageTitle(), imageObjectsActual.get(1).getImageTitle());
		Assert.assertEquals(imageObjects.get(1).getLikes(), imageObjectsActual.get(1).getLikes());
		
		
		Assert.assertEquals(new ArrayList<IBaseImage>(), imgLoader2.getImageObjects());
	}
}
