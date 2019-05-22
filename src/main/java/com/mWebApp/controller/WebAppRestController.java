package com.mWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mWebApp.model.User;
import com.mWebApp.service.UserService;


@Controller
public class WebAppRestController {
	
	@Autowired
    UserService userService;
	
	@RequestMapping(value = "/login/" , method=RequestMethod.GET)
	public String login(@ModelAttribute(name="user") User User) {
		return "Login";
	}
	
	@RequestMapping(value = "/login/" , method=RequestMethod.POST)
	public String loggingIn(@ModelAttribute(name="user") User User) {
		return "redirect:/track/";
	}
	
	@RequestMapping(value = "/track/" , method=RequestMethod.GET)
	public String Track(@ModelAttribute(name="user") User User) {
		return "Track";
	}

}
