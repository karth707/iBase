package com.iBase.domain;

import org.springframework.web.multipart.MultipartFile;

public class ImageFile {

	private MultipartFile imageFile;

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
}
