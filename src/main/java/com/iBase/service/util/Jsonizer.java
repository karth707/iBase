package com.iBase.service.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.iBase.domain.IBaseImage;

public class Jsonizer {

	private ObjectMapper mapper;
	public Jsonizer(){
		mapper = new ObjectMapper();
	}

	public String jsonize(Object obj){
		
		String json = null;
		try {
			 json = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public static void main(String[] args){
		
		Jsonizer jMaker = new Jsonizer();
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
		System.out.println(jMaker.jsonize(images));
	}
}
