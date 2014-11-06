package com.iBase.web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login.htm")
    public String handleHomeRequest(Model model){
		String now = (new Date()).toString();
		model.addAttribute("now", now);
		return "login";
	}
	
}
