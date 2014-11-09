package com.iBase.domain;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IBaseImageTest {
	
	private IBaseImage iBaseImage;
	
	@Before 
    public void initialize() throws Exception {
		iBaseImage = new IBaseImage();
    }
	
	@Test
	public void testSetAndGetImageLocation(){
		String imageLocation = "dummy_location";
		Assert.assertNull(iBaseImage.getImageLocation());
		iBaseImage.setImageLocation(imageLocation);
		assertEquals(imageLocation, iBaseImage.getImageLocation());
	}
	
	@Test
	public void testSetAndGetLikes(){
		int likes = 4;
		assertEquals(0, iBaseImage.getLikes());
		iBaseImage.setLikes(likes);
		assertEquals(likes, iBaseImage.getLikes());
	}
	
	@Test
	public void testSetAndGetImageId(){
		String imageId = "id";
		assertNull(iBaseImage.getImageId());
		iBaseImage.setImageId(imageId);
		assertEquals(imageId, iBaseImage.getImageId());
	}
	
	@Test
	public void testSetAndGetImageTitle(){
		String imageTitle = "title";
		assertNull(iBaseImage.getImageTitle());
		iBaseImage.setImageTitle(imageTitle);
		assertEquals(imageTitle, iBaseImage.getImageTitle());
	}
	
	
}
