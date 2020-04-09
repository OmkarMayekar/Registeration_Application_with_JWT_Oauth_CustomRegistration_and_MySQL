package com.snapshotprojects.Bingofy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class TemplateController {

	@GetMapping("login")
	public String getLoginView(Model model) {
		System.out.println("getLoginView() controller called");
		return "login";
	}

	@GetMapping("courses")
	public String getCourses() {
		return "courses";
	}
}