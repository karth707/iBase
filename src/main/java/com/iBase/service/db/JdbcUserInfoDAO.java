package com.iBase.service.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.iBase.domain.UserInfo;

public class JdbcUserInfoDAO implements UserInfoDAO{

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public void insert(UserInfo userInfo) {
		String sql = "INSERT INTO userInfo " +
				"(userId, password, friendList, imagesList, imageCount) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userInfo.getUserId());
			ps.setString(2, userInfo.getPassword());
			ps.setString(3, userInfo.getFriendList());
			ps.setString(4, userInfo.getImagesList());
			ps.setInt(5, userInfo.getImageCount());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	public void updateTable(UserInfo userInfo){
		String sql = "UPDATE userInfo " +
				"SET imagesList = ?, imageCount = ? WHERE userId = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userInfo.getImagesList());
			ps.setInt(2, userInfo.getImageCount());
			ps.setString(3, userInfo.getUserId());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public UserInfo findById(String userId) {
		
		String sql = "SELECT * FROM userInfo WHERE userId = ?";
		 
		Connection conn = null;
		 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			UserInfo userInfo = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userInfo = new UserInfo(
					rs.getString("userId"), 
					rs.getString("password"),
					rs.getString("friendList"),
					rs.getString("imagesList"),
					rs.getInt("imageCount")
				);
			}
			rs.close();
			ps.close();
			return userInfo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}
