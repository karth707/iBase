package com.iBase.web;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.iBase.domain.ProfileImageFile;
import com.iBase.domain.UserInfo;
import com.iBase.service.db.UserInfoDAO;

@Controller
public class SettingsController implements HandlerExceptionResolver{
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
    @RequestMapping(value="/settings", method = RequestMethod.GET)
    public String handleSettingsRequest(Model model)
    {
    	ProfileImageFile profileImageModel = new ProfileImageFile();
    	model.addAttribute("profileImageFile", profileImageModel);
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("userName", userDetail.getUsername());
			logger.info("Returning settings View!");
			return "settings";
		}
		 return "403";
    }
    
    @RequestMapping(value="/settings", method = RequestMethod.POST)
	public String profileImageFileUploaded(@ModelAttribute(value="profileImageFile") ProfileImageFile profileImageModel
			, Model model, BindingResult result ){
    	
    	String userName = null;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("userName", userDetail.getUsername());
			userName = userDetail.getUsername();
		}
    	
    	if(!result.hasErrors()){
    		String fileName = profileImageModel.getProfileImageFile().getOriginalFilename();
			if(!isValidFile(fileName)){
				model.addAttribute("uploadInfo", "Sorry! Invalid File!");
				return "settings";
			}
			FileOutputStream outputStream = null;
			String rootPath = System.getProperty("catalina.home"); //localhost
			//String rootPath = System.getProperty("catalina.base");	//server
			logger.info("rootpath:" +rootPath);
			File dir = new File(rootPath + File.separator 
					+ "webapps" + File.separator + "iBase" + File.separator 
					+ "resources" + File.separator 
					+ "images" + File.separator + userName);
			if (!dir.exists()){
				
				boolean x = dir.mkdirs();
				logger.info(x);
			}
			
			UserInfo user = getUserInfo(userName);
			String newimageLocation = dir.getAbsolutePath()
					+ File.separator 
					+ "profile.jpg";
			
			String dbLocation = File.separator + "resources" + File.separator 
					+  "images" + File.separator + userName
					+ File.separator 
					+ "profile.jpg";
			
			boolean update = updateDB(user, dbLocation);  
			if(update==false){
				model.addAttribute("uploadInfo", "Sorry! DataBase update Error!");
				return "settings";
			}
			File profileImageFile = new File(newimageLocation);
			if(profileImageFile.exists()){
				profileImageFile.delete();
				profileImageFile = new File(newimageLocation);
			}
			
			try {				
				outputStream = new FileOutputStream(profileImageFile);
				outputStream.write(profileImageModel.getProfileImageFile().getFileItem().get());
				outputStream.close();
			}catch (Exception e) {
				logger.error("Error while saving file");
				return "settings";
			}
			logger.info("Server File Location="+profileImageFile.getAbsolutePath());
			model.addAttribute("uploadInfo", "Profile picture successfully updated!");
			return "settings";
		}else{
			model.addAttribute("uploadInfo", "Sorry! Invalid File!");
			return "settings";
		}
    }
    
    private boolean updateDB(UserInfo user, String dbLocation) {

		user.setProfilePic(dbLocation);
		try{
			userInfoDAO.updateProfilePicture(user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
    	return false;
	}

	private boolean isValidFile(String fileName) {

		if(fileName.contains(".jpg") || fileName.contains(".jpeg")){
			return true;
		}
		return false;
	}

	private UserInfo getUserInfo(String userName) {
		UserInfo user = userInfoDAO.findById(userName);
		return user;
	}
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<Object, Object> model = new HashMap<Object, Object>();
		if (ex instanceof MaxUploadSizeExceededException){
			model.put("errors2", "File size should be less then "+
					((MaxUploadSizeExceededException)ex).getMaxUploadSize()+" byte.");
		}else{
			model.put("errors2", "Unexpected error!!!: " + ex.getMessage());
			ex.printStackTrace();
		}
		model.put("profileImageFile", new ProfileImageFile());
		return new ModelAndView("/settings", (Map) model);
	}
    
    public UserInfoDAO getUserInfoDAO() {
		return userInfoDAO;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
}

