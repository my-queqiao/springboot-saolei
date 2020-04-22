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
		return "index";
	}
	
	@Autowired
	private TestBean tb;
	
	@SecurityIgnoreHandler
	@RequestMapping("/single")
	@ResponseBody
	public String single() {
	        tb.setAge(tb.getAge() + 10 );
	        // 每次输出的hashcode一样，同一个实例。age在累加，也证明是单例。
	        return String.valueOf(tb.getAge()) + "===>" + this.hashCode();   
	}
}
