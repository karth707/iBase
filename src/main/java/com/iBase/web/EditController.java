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

import com.iBase.domain.IBaseImage;
import com.iBase.domain.UserInfo;
import com.iBase.service.ImageLoader;
import com.iBase.service.db.UserInfoDAO;

@Controller
public class EditController {

	protected final Log log = LogFactory.getLog(getClass());
	ImageLoader imageLoader;
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@RequestMapping(value="/edit/{imageId}", method = RequestMethod.GET)
	public String byParameter(@PathVariable String imageId, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("userName", userDetail.getUsername());
			String userName = userDetail.getUsername();
			model.addAttribute("imageId", imageId);
			UserInfo user = userInfoDAO.findById(userName);
			imageLoader = new ImageLoader(user);
			IBaseImage image = imageLoader.getImageObjects().get(Integer.parseInt(imageId)-1);
			model.addAttribute("imageLocation", image.getImageLocation());
			model.addAttribute("imageTitle", image.getImageTitle());
			return "edit";
		}
		return "403";
	}
}
