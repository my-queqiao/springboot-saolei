package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.annotation.SecurityIgnoreHandler;
import com.example.biz.LiuyanBiz;
import com.example.pojo.Liuyan;
@Controller
@RequestMapping("/")
public class LiuyanSaoleiController {
	@Autowired
	private LiuyanBiz liuyanBiz;
	
	
	@SecurityIgnoreHandler
	@RequestMapping("toLiuyanSaolei")
	public String toLiuyan(){
		System.out.println("测试dev差异");
			return "liuyanSaolei";
	}
	
	@SecurityIgnoreHandler
	@RequestMapping("liuyanSaolei")
	@ResponseBody
	public JSONObject liuyan(String liuyan){
		System.out.println("测试dev差异");
		Liuyan ly = new Liuyan();
		ly.setLiuyan(liuyan);
		ly.setTime(new Date());
		liuyanBiz.save(ly);
		JSONObject json = new JSONObject();
		json.put("result", true);
		json.put("msg", "留言成功");
		return json;
	}
	@SecurityIgnoreHandler
	@RequestMapping("liuyanSaolei2")
	@ResponseBody
	public void liuyan2(HttpServletResponse response) throws IOException{
		// 测试dev差异
		String html = "<div style='color:green'>success</div>";
        PrintWriter pw = response.getWriter();
        pw.println(html);
	}
	
	
	@RequestMapping("getLeaveMsgSaolei")
	@ResponseBody
	public JSONObject getLeaveMsg(){
		/*
		 *	测试dev差异 
		 */
		/**
		 * 测试dev差异2
		 */
		List<Liuyan> lys = liuyanBiz.getAll();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Liuyan ly:lys){
			ly.setTimeString(df.format(ly.getTime()));
		}
		JSONObject json = new JSONObject();
		json.put("result", true);
		json.put("data", lys);
		return json;
	}
}
