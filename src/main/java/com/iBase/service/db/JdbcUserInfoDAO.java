package com.iBase.service.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.iBase.domain.UserInfo;
import com.iBase.domain.UserRole;

public class JdbcUserInfoDAO implements UserInfoDAO{
	
	protected final Log logger = LogFactory.getLog(getClass());

	private JdbcTemplate jdbcTemplate;
	public JdbcUserInfoDAO(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
//	private DataSource dataSource;
//	
//	public void setDataSource(DataSource dataSource){
//		this.dataSource = dataSource;
//	}
	
	public void insert(UserInfo userInfo){
		String sql = "INSERT INTO userInfo "
				+ "(userId, password, friendList, imagesList, imageCount, firstName, lastName) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		logger.info("Running query: " + sql);
		jdbcTemplate.update(sql, userInfo.getUserId(), userInfo.getPassword()
				, userInfo.getFriendList(), userInfo.getImagesList(), userInfo.getImageCount()
				, userInfo.getFirstName(), userInfo.getLastName());
	}
	
	public void insertUserRole(UserRole userRoles){
		String sql = "INSERT INTO user_roles " 
					+ "(userId, ROLE)"
					+ "VALUES (?, ?)";
		logger.info("Running query: " + sql);
		jdbcTemplate.update(sql, userRoles.getUserId(), userRoles.getRole());
	}
	
//	public void insert(UserInfo userInfo) {
//		String sql = "INSERT INTO userInfo " +
//				"(userId, password, friendList, imagesList, imageCount) VALUES (?, ?, ?, ?, ?)";
//		Connection conn = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, userInfo.getUserId());
//			ps.setString(2, userInfo.getPassword());
//			ps.setString(3, userInfo.getFriendList());
//			ps.setString(4, userInfo.getImagesList());
//			ps.setInt(5, userInfo.getImageCount());
//			ps.executeUpdate();
//			ps.close();
// 
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
// 
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {}
//			}
//		}
//	}

	public void updateTable(UserInfo userInfo){
	
		String sql = "UPDATE userInfo " +
				"SET imagesList = ?, imageCount = ? WHERE userId = ?";
		logger.info("Running query: " + sql);
		jdbcTemplate.update(sql, userInfo.getImagesList()
				, userInfo.getImageCount(), userInfo.getUserId());
	}
	
	
//	public void updateTable(UserInfo userInfo){
//		String sql = "UPDATE userInfo " +
//				"SET imagesList = ?, imageCount = ? WHERE userId = ?";
//		Connection conn = null;
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, userInfo.getImagesList());
//			ps.setInt(2, userInfo.getImageCount());
//			ps.setString(3, userInfo.getUserId());
//			ps.executeUpdate();
//			ps.close();
// 
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
// 
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {}
//			}
//		}
//	}
	
	public UserInfo findById(String userId) {
		String sql = "SELECT * FROM userInfo WHERE userId = "+"\""+userId+"\"";
		logger.info("Running query: " + sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<UserInfo>(){
			public UserInfo extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
					UserInfo userInfo = new UserInfo(rs.getString("userId")
							, rs.getString("password")
							, rs.getString("friendList"), rs.getString("imagesList")
							, rs.getInt("imageCount"));
					return userInfo;
				}
				return null;
			}
		});	
	}
	
	
//	public UserInfo findById(String userId) {
//		
//		String sql = "SELECT * FROM userInfo WHERE userId = ?";
//		 
//		Connection conn = null;
//		 
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, userId);
//			UserInfo userInfo = null;
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				userInfo = new UserInfo(
//					rs.getString("userId"), 
//					rs.getString("password"),
//					rs.getString("friendList"),
//					rs.getString("imagesList"),
//					rs.getInt("imageCount")
//				);
//			}
//			rs.close();
//			ps.close();
//			return userInfo;
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			if (conn != null) {
//				try {
//				conn.close();
//				} catch (SQLException e) {}
//			}
//		}
//	}
}
