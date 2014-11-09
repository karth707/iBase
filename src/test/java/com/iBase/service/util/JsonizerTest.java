package com.iBase.service.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.iBase.domain.IBaseImage;

public class JsonizerTest {

	private Jsonizer jsonizer;
	
	@Before 
    public void initialize() throws Exception {
		jsonizer = new Jsonizer();
	}
	
	@Test
	public void testJsonize(){
		IBaseImage image1 = new IBaseImage();
		IBaseImage image2 = new IBaseImage();
		image1.setImageLocation("/Users/KartheekGanesh/Sources/iBaseTestImages/1.jpg");
		image1.setLikes(0);
		image1.setImageId("1");
		image1.setImageTitle("testImage1");
		image2.setImageLocation("/Users/KartheekGanesh/Sources/iBaseTestImages/2.jpg");
		image2.setLikes(0);
		image2.setImageId("2");
		image2.setImageTitle("testImage2");
		List<IBaseImage> images = new ArrayList<IBaseImage>();
		images.add(image1);
		images.add(image2);
		String expected = "[{\"imageLocation\":\"/Users/KartheekGanesh/Sources/iBaseTestImages/1.jpg\""
				+ ",\"imageId\":\"1\",\"imageTitle\":\"testImage1\",\"likes\":0}"
				+ ",{\"imageLocation\":\"/Users/KartheekGanesh/Sources/iBaseTestImages/2.jpg\""
				+ ",\"imageId\":\"2\",\"imageTitle\":\"testImage2\",\"likes\":0}]";
		
		assertEquals(expected, jsonizer.jsonize(images));
	}
}
