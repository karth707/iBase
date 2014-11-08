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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.iBase.domain.ImageFile;
import com.iBase.web.validators.ImageFileValidator;

@Controller
@RequestMapping("/upload")
public class FileController implements HandlerExceptionResolver{

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	ImageFileValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getForm(Model model)
    {
		ImageFile imageModel = new ImageFile();
		model.addAttribute("imageFile", imageModel);
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("userName", userDetail.getUsername());
			return "upload";
		}
		return "403";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String imageFileUploaded(@ModelAttribute(value="imageFile") ImageFile imageModel
			, BindingResult result){

		if(!result.hasErrors()){
			FileOutputStream outputStream = null;
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator + "images");
			if (!dir.exists())
				dir.mkdirs();
			File imageFile = new File(dir.getAbsolutePath()+File.separator + imageModel.getImageFile().getOriginalFilename());
			//String filePath = System.getProperty("java.io.tmpdir") + "/" + imageModel.getImageFile().getOriginalFilename();
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
}
