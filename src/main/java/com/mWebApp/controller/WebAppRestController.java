package com.mWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebAppRestController {
	
	@RequestMapping(value = "/login/")
	public String login() {
		return "Login";
	}

}
