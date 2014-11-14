package com.iBase.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ProfileImageFile {

	private CommonsMultipartFile profileImageFile = null;

	public CommonsMultipartFile getProfileImageFile() {
		return profileImageFile;
	}

	public void setProfileImageFile(CommonsMultipartFile profileImageFile) {
		this.profileImageFile = profileImageFile;
	}
}

