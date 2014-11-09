package com.iBase.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iBase.domain.UserInfo;
import com.iBase.service.ImageLoader;
import com.iBase.service.db.UserInfoDAO;

@Controller
public class HomeController {

	protected final Log log = LogFactory.getLog(getClass());
	//private String userId = "test1@asu.edu";
	ImageLoader imageLoader;
	
	@Autowired
	private UserInfoDAO userInfoDAO;

	@RequestMapping(value={"/", "/home*"}, method = RequestMethod.GET)
    public String handleHomeRequest(Model model){
    	
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("userName", userDetail.getUsername());
			String userId = userDetail.getUsername();
			String now = (new Date()).toString();
	        log.info("Returning home to "+ userId + " view at " + now);
	        model.addAttribute("now", now);
	        
	        //get the images
	        List<String> imagesLocation = getImages(userId);
	        if(imagesLocation!=null){
	        	log.info(imagesLocation);
		        model.addAttribute("imageList", imagesLocation);
		        //add to the model to display on page
	        }else{
	        	model.addAttribute("imagesMessage", "No Images to display!");
	        }
			return "home";
		}
        return "403";
    }
	
	private List<String> getImages(String userId2) {
		UserInfo user = userInfoDAO.findById(userId2);
        imageLoader = new ImageLoader(user);
        return imageLoader.getImageLocations();
	}

	public UserInfoDAO getUserInfoDAO() {
		return userInfoDAO;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
}