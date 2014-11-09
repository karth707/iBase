package com.iBase.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iBase.domain.NewUser;
import com.iBase.domain.UserInfo;
import com.iBase.domain.UserRole;
import com.iBase.service.db.UserInfoDAO;

@Controller
@RequestMapping(value = {"/signup", "/register"})
public class RegisterController {

	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {
        NewUser userForm = new NewUser();    
        model.put("userForm", userForm);
        return "signup";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userForm") NewUser newUser,
            Map<String, Object> model) {

		if(userExists(newUser.getEmail())){
			model.put("newUserError", "Username Taken!");
			return "signup";
		}else{
			updateDB(newUser);
			return "success";
		}
	}

	private void updateDB(NewUser newUser) {
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName(newUser.getFirstName());
		userInfo.setFriendList(null);
		userInfo.setImageCount(0);
		userInfo.setImagesList(null);
		userInfo.setLastName(newUser.getLastName());
		userInfo.setPassword(newUser.getPassword());
		userInfo.setUserId(newUser.getEmail());
		userInfoDAO.insert(userInfo);
		
		UserRole userRole = new UserRole();
		userRole.setRole("ROLE_USER");
		userRole.setUserId(newUser.getEmail());
		userInfoDAO.insertUserRole(userRole);
	}

	private boolean userExists(String email) {
		if(userInfoDAO.findById(email)!=null){
			return true;
		}
		return false;
	}
	
}
