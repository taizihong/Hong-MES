package com.hong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/admin")
public class AdminController {

	@RequestMapping("/index.page")
	public String index() {
		return "admin";
	}
}
