package com.iBase.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iBase.domain.UserInfo;
import com.iBase.service.ImageLoader;
import com.iBase.service.db.UserInfoDAO;

@Controller
public class HomeController {

	protected final Log log = LogFactory.getLog(getClass());
	private String userId = "test1@asu.edu";
	ImageLoader imageLoader;
	
	@Autowired
	private UserInfoDAO userInfoDAO;

	@RequestMapping("/home.htm")
    public String handleHomeRequest(Model model){
    	
		String now = (new Date()).toString();
        log.info("Returning home to "+ userId + " view at " + now);
        model.addAttribute("now", now);
        
        //get the images
        List<String> imagesLocation = getImages(userId);
        log.info(imagesLocation);
        
        //add to the model to display on page
        
        return "home";
    }
	
	private List<String> getImages(String userId2) {
		UserInfo user = userInfoDAO.findById(userId);
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
