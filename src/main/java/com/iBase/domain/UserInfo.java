package com.iBase.domain;

public class UserInfo {

	private int id;
	private String userId;
	private String password;
	private String friendList;
	private String imagesList;
	
	public UserInfo(int id, String userId, String password
			, String friendList, String imagesList){
		this.id = id;
		this.friendList = friendList;
		this.imagesList = imagesList;
		this.password = password;
		this.userId = userId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
}