package com.iBase.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.iBase.domain.IBaseImage;
import com.iBase.domain.UserInfo;

public class ImageLoader {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	private ObjectMapper mapper;
	private UserInfo user;
	
	public ImageLoader(UserInfo user) {
		this.user = user;
		mapper = new ObjectMapper();
	}

	public List<IBaseImage> getImageObjects() {
		List<IBaseImage> images = new ArrayList<IBaseImage>();
		String imagesJSON = user.getImagesList();
		if(imagesJSON==null){
			return images;
		}
		try {
			ArrayList<IBaseImage> IBaseImages = mapper.readValue(imagesJSON
					, new TypeReference<ArrayList<IBaseImage>>(){});
			for(IBaseImage image: IBaseImages){
				images.add(image);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return images;
	}
}
