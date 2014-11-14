package com.iBase.domain;

public class UserInfo {

	private String userId;
	private String password;
	private String friendList;
	private String imagesList;
	private int imageCount;
	private String firstName;
	private String lastName;
	private String profilePic;
	
	public UserInfo(){
		
	}
	public UserInfo(String userId, String password
			, String friendList, String imagesList, int imageCount, String profilePic){
		this.friendList = friendList;
		this.imagesList = imagesList;
		this.password = password;
		this.userId = userId;
		this.imageCount = imageCount;
		this.profilePic = profilePic;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	
}
