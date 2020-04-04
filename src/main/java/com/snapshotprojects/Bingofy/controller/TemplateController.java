package com.snapshotprojects.Bingofy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TemplateController {

	@GetMapping("login")
	public String getLoginView() {
		System.out.println("getLoginView() controller called");
		return "login";
	}

	@GetMapping("courses")
	public String getCourses() {
		return "courses";
	}
}