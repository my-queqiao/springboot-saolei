package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.annotation.SecurityIgnoreHandler;

import net.sf.json.JSONObject;

@Controller
//@Scope("prototype") // singleton、prototype
public class Index_xinzeng {
	/*
	 * 默认首页
	 */
	@SecurityIgnoreHandler
	@RequestMapping("/xinzeng")
	@ResponseBody
	public JSONObject xinzeng() {
		JSONObject json = new JSONObject();
		json.put("success", true);
		return json;
	}
}
