package com.iBase.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iBase.domain.ImageFile;
import com.iBase.web.validators.ImageFileValidator;

@Controller
@RequestMapping("/upload.htm")
public class FileController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	ImageFileValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getForm(Model model) {
		ImageFile imageModel = new ImageFile();
		model.addAttribute("imageFile", imageModel);
		return "upload";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String imageFileUploaded(Model model
			, @Validated @RequestParam("imageFile") ImageFile file
			, BindingResult result){
		String returnVal = "uploadSuccess";
		if(result.hasErrors()){
			returnVal = "upload"; 
		}else{
			MultipartFile imageFile = file.getImageFile();
		}
		return returnVal;
	}
}
