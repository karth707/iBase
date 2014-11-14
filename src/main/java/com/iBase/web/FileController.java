package com.iBase.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
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

import com.iBase.domain.IBaseImage;
import com.iBase.domain.ImageFile;
import com.iBase.domain.UserInfo;
import com.iBase.service.db.UserInfoDAO;
import com.iBase.service.util.Jsonizer;

@Controller
//@RequestMapping("/upload")
public class FileController implements HandlerExceptionResolver{

	protected final Log logger = LogFactory.getLog(getClass());
	private ObjectMapper mapper = new ObjectMapper();
	private Jsonizer jsonizer = new Jsonizer();

	//@Autowired
	//ImageFileValidator validator;
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
//	@InitBinder
//	private void initBinder(WebDataBinder binder){
//		binder.setValidator(validator);
//	}
	
	@RequestMapping(value="/upload", method = RequestMethod.GET)
	public String getForm(Model model)
    {
		ImageFile imageModel = new ImageFile();
		model.addAttribute("imageFile", imageModel);
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("userName", userDetail.getUsername());
			logger.info("Returning upload View!");
			return "upload";
		}
		return "403";
	}
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String imageFileUploaded(@ModelAttribute(value="imageFile") ImageFile imageModel
			, Model model, BindingResult result){

		String userName = null;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("userName", userDetail.getUsername());
			userName = userDetail.getUsername();
		}
		
		if(!result.hasErrors()){
			
			String fileName = imageModel.getImageFile().getOriginalFilename();
			if(!isValidFile(fileName)){
				model.addAttribute("uploadLimit", "Sorry! Invalid File!");
				return "upload";
			}
			FileOutputStream outputStream = null;
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator 
					+ "webapps" + File.separator + "iBase" + File.separator 
					+ "resources" + File.separator 
					+ "images" + File.separator + userName);
			if (!dir.exists())
				dir.mkdirs();
			UserInfo user = getUserInfo(userName);

			String newimageLocation = dir.getAbsolutePath()
					+ File.separator 
					+ Integer.toString(user.getImageCount()+1)+".jpg";
			
			String dbLocation = File.separator + "resources" + File.separator 
					+  "images" + File.separator + userName
					+ File.separator 
					+ Integer.toString(user.getImageCount()+1)+".jpg";
			
			boolean update = updateDB(user, dbLocation);
			if(update==false){
				model.addAttribute("uploadLimit", "Sorry! DataBase update Error!");
				return "upload";
			}
			File imageFile = new File(newimageLocation);
			imageModel.setName(imageModel.getImageFile().getOriginalFilename());
			
			try {
				outputStream = new FileOutputStream(imageFile);
				outputStream.write(imageModel.getImageFile().getFileItem().get());
				outputStream.close();
			}catch (Exception e) {
				logger.error("Error while saving file");
				return "upload";
			}
			logger.info("Server File Location="+imageFile.getAbsolutePath());
			return "uploadSuccess";
		}else{
			return "upload";
		}
	}

	private boolean isValidFile(String fileName) {

		if(fileName.contains(".jpg") || fileName.contains(".jpeg")
				|| fileName.contains(".png") || fileName.contains(".gif")){
			return true;
		}
		return false;
	}

	private UserInfo getUserInfo(String userName) {
		UserInfo user = userInfoDAO.findById(userName);
		return user;
	}

	private boolean updateDB(UserInfo user, String newimageLocation) {
		
		try{
			List<IBaseImage> imagesLocations = getImageList(user);
			if(imagesLocations==null){
				imagesLocations = new ArrayList<IBaseImage>();
			}
			if(user.getImageCount()==10){
				return false;
			}
			IBaseImage newImage = new IBaseImage();
			newImage.setImageId(Integer.toString(user.getImageCount()+1));
			newImage.setImageLocation(newimageLocation);
			newImage.setLikes(0);
			newImage.setImageTitle("imge number: "+Integer.toString(user.getImageCount()+1));
			imagesLocations.add(newImage);
			String imagesList = jsonizer.jsonize(imagesLocations);
			user.setImagesList(imagesList);
			user.setImageCount(user.getImageCount()+1);
			userInfoDAO.updateTable(user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	private List<IBaseImage> getImageList(UserInfo user) {
		ArrayList<IBaseImage> iBaseImages = null;
		String imagesJSON = user.getImagesList();
		if(imagesJSON==null){
			return iBaseImages;
		}
		try {
			iBaseImages = mapper.readValue(imagesJSON
					, new TypeReference<ArrayList<IBaseImage>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return iBaseImages;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<Object, Object> model = new HashMap<Object, Object>();
		if (ex instanceof MaxUploadSizeExceededException){
			model.put("errors", "File size should be less then "+
					((MaxUploadSizeExceededException)ex).getMaxUploadSize()+" byte.");
		}else{
			model.put("errors", "Unexpected error: " + ex.getMessage());
		}
		model.put("imageFile", new ImageFile());
		return new ModelAndView("/upload", (Map) model);
	}
	
	public UserInfoDAO getUserInfoDAO() {
		return userInfoDAO;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
}
