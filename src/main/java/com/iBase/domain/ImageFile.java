package com.iBase.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ImageFile {

	private CommonsMultipartFile imageFile = null;
	private String name = "n/a";

	public CommonsMultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(CommonsMultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
