package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {
	/*
	 * 默认首页
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
