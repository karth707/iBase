package com.iBase.service.db;

import com.iBase.domain.UserInfo;
import com.iBase.domain.UserRole;

public interface UserInfoDAO {
	public boolean insert(UserInfo userInfo);
	public UserInfo findById(String userId);
	public boolean updateTable(UserInfo userInfo);
	public boolean insertUserRole(UserRole userRoles);
	public boolean updateProfilePicture(UserInfo userInfo);
}
