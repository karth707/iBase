package com.iBase.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iBase.domain.UserInfo;
import com.iBase.service.db.UserInfoDAO;

@Controller
public class UserProfileController {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@RequestMapping(value="/userProfile/{friendId:.+}", method = RequestMethod.GET)
	public String getUserProfile(@PathVariable String friendId, Model model){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("userName", userDetail.getUsername());
			model.addAttribute("friendId", friendId);
			UserInfo user = userInfoDAO.findById(userDetail.getUsername());
			model.addAttribute("fName", user.getFirstName());
	        model.addAttribute("lName", user.getLastName());
	        
	        UserInfo friendInfo = userInfoDAO.findById(friendId);
	        model.addAttribute("friendFName", friendInfo.getFirstName());
	        model.addAttribute("friendLName", friendInfo.getLastName());
	        model.addAttribute("friendEmail", friendId);
	        model.addAttribute("friendImageCount", friendInfo.getImageCount());
	        
	        return "userProfile";
		}
		return "403";
	}
}
