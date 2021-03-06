package com.mWebApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

import com.mWebApp.model.User;
import com.mWebApp.service.UserService;

@Controller
public class WebAppController {
	
	@Autowired
    private UserService userService;
	
	@RequestMapping(value = "/login/" , method=RequestMethod.GET)
	public String login(@ModelAttribute (name="user") User user) {
		return "Login";
	}
	
	@RequestMapping(value="/validateCreate")
	public String validateCreate(@Valid @ModelAttribute (name="user") User user, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("Form has errors");
			return "Login";
		}else if(userService.isAdmin(user)){
			return "redirect:/admin/";
		}
		
		return "redirect:/track/";
	}
	
	@RequestMapping(value = "/track/" , method=RequestMethod.GET)
	public String track() {
		return "Track";
	}
	
	@RequestMapping(value = "/admin/" , method=RequestMethod.GET)
	public String userAdmin() {
		return "Admin";
	}

}
