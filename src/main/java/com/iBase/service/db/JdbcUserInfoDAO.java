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
	
	public boolean insert(UserInfo userInfo){
		try{
			String sql = "INSERT INTO userInfo "
					+ "(userId, password, friendList, imagesList, imageCount, firstName, lastName) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			logger.info("Running query: " + sql);
			jdbcTemplate.update(sql, userInfo.getUserId(), userInfo.getPassword()
					, userInfo.getFriendList(), userInfo.getImagesList(), userInfo.getImageCount()
					, userInfo.getFirstName(), userInfo.getLastName());
			return true;
		}catch(Exception ex){
			logger.error("Database insert failed...");
		}
		return false;
	}
	
	public boolean insertUserRole(UserRole userRoles){
		try{
			String sql = "INSERT INTO user_roles " 
						+ "(userId, ROLE)"
						+ "VALUES (?, ?)";
			logger.info("Running query: " + sql);
			jdbcTemplate.update(sql, userRoles.getUserId(), userRoles.getRole());
			return true;
		}catch(Exception ex){
			logger.error("Database insertUserRole failed...");
		}
		return false;
	}
	
	public boolean updateTable(UserInfo userInfo){
	
		try{
			String sql = "UPDATE userInfo " +
					"SET imagesList = ?, imageCount = ? WHERE userId = ?";
			logger.info("Running query: " + sql);
			jdbcTemplate.update(sql, userInfo.getImagesList()
					, userInfo.getImageCount(), userInfo.getUserId());
			return true;
		}catch(Exception ex){
			logger.error("Databse updateTable failed...");
		}
		return false;
	}
	
	public boolean updateProfilePicture(UserInfo userInfo){
		try{
			String sql = "UPDATE userInfo " +
					"SET profilePic = ? WHERE userId = ?";
			logger.info("Running query: " + sql);
			jdbcTemplate.update(sql, userInfo.getProfilePic(), userInfo.getUserId());
			return true;
		}catch(Exception ex){
			logger.error("Database updateProfilePicture failed...");
		}
		return false;
	}
	
	public UserInfo findById(String userId) {
		String sql = "SELECT * FROM userInfo WHERE userId = "+"\""+userId+"\"";
		logger.info("Running query: " + sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<UserInfo>(){
			public UserInfo extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
					UserInfo userInfo = new UserInfo(rs.getString("userId")
							, rs.getString("password")
							, rs.getString("firstName")
							, rs.getString("lastName")
							, rs.getString("friendList"), rs.getString("imagesList")
							, rs.getInt("imageCount"), rs.getString("profilePic"));
					return userInfo;
				}
				return null;
			}
		});	
	}
}
