package com.iBase.web;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iBase.domain.NewUser;
import com.iBase.domain.UserInfo;
import com.iBase.domain.UserRole;
import com.iBase.service.db.UserInfoDAO;
import com.iBase.web.sharing.FriendGraph;

@Controller
@RequestMapping(value = {"/signup", "/signup/register", "/register"})
public class RegisterController {

	private FriendGraph friendGraph;
	protected final Log logger = LogFactory.getLog(getClass());
	
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

		friendGraph = new FriendGraph();
		if(newUser.getFirstName()=="" || newUser.getLastName()==""
				|| newUser.getLastName()==null || newUser.getFirstName()==null){
			model.put("newUserError", "Please enter a valid Name");
			return "signup";
		}
		if(userExists(newUser.getEmail())){
			model.put("newUserError", "Username Taken!");
			return "signup";
		}else if(!(newUser.getEmail().contains("@")) 
				&& !(newUser.getEmail().contains("."))){
			model.put("newUserError", "invalid email address!");
			return "signup";
		}else if(newUser.getPassword().length()<6){
			model.put("newUserError", "password must be atleast 6 characters!");
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
		
		//add user to friend graph
		friendGraph.addNode(newUser.getEmail());
	}

	private boolean userExists(String email) {
		if(userInfoDAO.findById(email)!=null){
			return true;
		}
		return false;
	}
	
}
