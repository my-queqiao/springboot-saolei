package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.annotation.SecurityIgnoreHandler;
import com.example.biz.ChangeCodeBiz;
import com.example.biz.StableReleaseCodeBiz;
import com.example.biz.TestingExampleBiz;
import com.example.pojo.ChangeCode;
import com.example.pojo.StableReleaseCode;
import com.example.pojo.TestingExample;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("changeCode")
public class ChangeCodeController {
	@Autowired
	private ChangeCodeBiz changeCodeBiz;
	/**
	 * 精准测试项目首页
	 * @return
	 */
	@SecurityIgnoreHandler
	@RequestMapping("index")
	public String index() {
		return "cc_index";
	}
	/**
	 * 获取测试用例列表
	 * @param pageNumber 页码
	 * @param pageSize 每页行数
	 * @param search
	 * @return
	 */
	@SecurityIgnoreHandler
	@RequestMapping("getAll")
	@ResponseBody
	public JSONObject getList(Integer pageNumber,Integer pageSize,Integer search) {
		JSONObject json = new JSONObject();
//		List<StableReleaseCode> list = stableReleaseCodeBiz.getAll(pageNumber,pageSize,search);
//		Integer total = stableReleaseCodeBiz.findTotal(search);
		List<ChangeCode> page = changeCodeBiz.page(pageNumber, pageSize, search);
		Integer total = changeCodeBiz.findTotal(search);
		json.put("rows", page);
		json.put("total", total);
		return json;
	}
}































