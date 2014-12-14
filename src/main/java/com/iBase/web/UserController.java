package com.iBase.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iBase.domain.IBaseImage;
import com.iBase.domain.UserInfo;
import com.iBase.service.ImageLoader;
import com.iBase.service.db.UserInfoDAO;

@Controller
public class UserController {

	protected final Log log = LogFactory.getLog(getClass());
	ImageLoader imageLoader;
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@RequestMapping(value="/viewUser", method = RequestMethod.GET)
	public String handleViewUser(){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "viewUser";
		}
		return "403";
	}
	
	@RequestMapping(value="/user/{friendId:.+}", method = RequestMethod.GET)
	public String getUserImages(@PathVariable String friendId, Model model){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			//UserDetails userDetail = (UserDetails) auth.getPrincipal();
			//model.addAttribute("userName", userDetail.getUsername());
			//String userName = userDetail.getUsername();
			
			log.info("friend id:" + friendId);;
			UserInfo user = userInfoDAO.findById(friendId);
			model.addAttribute("friendId", friendId);
	        model.addAttribute("fName", user.getFirstName());
	        model.addAttribute("lName", user.getLastName());
	        
	        //get the images
	        List<IBaseImage> imageObjects = getImages(user);

	        
	        if(imageObjects.size()!=0){
	        	log.info(imageObjects);
		        model.addAttribute("imageObjects", imageObjects);
		        //add to the model to display on page
	        }else{
	        	model.addAttribute("imagesMessage", "No Images to display!");
	        }
	        return "viewUser";
		}
        return "403";
	}
	
	private List<IBaseImage> getImages(UserInfo user) {
        imageLoader = new ImageLoader(user);
        return imageLoader.getImageObjects();
	}
}
