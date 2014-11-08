package com.iBase.service.db;

import com.iBase.domain.UserInfo;

public interface UserInfoDAO {
	public void insert(UserInfo userInfo);
	public UserInfo findById(String userId);
	public void updateTable(UserInfo userInfo);
}
