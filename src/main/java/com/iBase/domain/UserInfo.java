package com.iBase.domain;

public class UserInfo {

	private String userId;
	private String password;
	private String friendList;
	private String imagesList;
	private int imageCount;
	
	public UserInfo(String userId, String password
			, String friendList, String imagesList, int imageCount){
		this.friendList = friendList;
		this.imagesList = imagesList;
		this.password = password;
		this.userId = userId;
		this.imageCount = imageCount;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImagesList() {
		return imagesList;
	}
	public void setImagesList(String imagesList) {
		this.imagesList = imagesList;
	}
	public String getFriendList() {
		return friendList;
	}
	public void setFriendList(String friendList) {
		this.friendList = friendList;
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}
	
}
