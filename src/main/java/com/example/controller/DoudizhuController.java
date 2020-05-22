package com.example.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.annotation.SecurityIgnoreHandler;
import com.example.pojo.Lei;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("")
public class DoudizhuController {
	
	@SecurityIgnoreHandler
	@RequestMapping("doudizhu")
	public String fanfankan(HttpSession session){
		return "doudizhu";
	}
	public static void main(String[] args) {
		// 单个奖品的中奖概率的计算方法
		// 1、确定一个随机数的范围，这个范围内数字的个数即为概率的分母。
		// 2、生成的随机数和范围内的任意n个数字比较，如果有一个相等的即为中奖。中奖概率就是：n/分母。
		int zjcs = 0;
		for(int i=0;i<10000;i++) {
			int a = (int)(Math.random()*10+1); // [1,10]
			if(a == 1 || a == 2 || a == 3) { // 百分之30的中奖概率
				zjcs++;
			}
		}
		System.out.println("中奖次数："+zjcs); // 中奖次数：3071
	}
}
