package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.annotation.SecurityIgnoreHandler;

@Controller
//@Scope("prototype") // singleton、prototype
public class Index {
	/*
	 * 默认首页
	 */
	@SecurityIgnoreHandler
	@RequestMapping("/")
	public String index() {
		System.out.println("修改了当前方法");
		return "index";
	}
	// 下面删除了一个方法
}
