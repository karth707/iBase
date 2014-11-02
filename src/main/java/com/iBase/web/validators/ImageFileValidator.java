package com.iBase.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iBase.domain.ImageFile;

public class ImageFileValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return ImageFile.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ImageFile imageFile = (ImageFile) target;
		if(imageFile.getImageFile().getSize()==0){
			errors.rejectValue("imageFile", "valid.file");
		}
	}

}
