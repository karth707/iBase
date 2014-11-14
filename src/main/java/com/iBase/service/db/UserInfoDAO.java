package com.iBase.service.db;

import com.iBase.domain.UserInfo;
import com.iBase.domain.UserRole;

public interface UserInfoDAO {
	public void insert(UserInfo userInfo);
	public UserInfo findById(String userId);
	public void updateTable(UserInfo userInfo);
	public void insertUserRole(UserRole userRoles);
	public void updateProfilePicture(UserInfo userInfo);
}
