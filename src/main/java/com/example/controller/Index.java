package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.annotation.SecurityIgnoreHandler;

@Controller
public class Index {
	/*
	 * 默认首页
	 */
	@SecurityIgnoreHandler
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
