package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.annotation.SecurityIgnoreHandler;
import com.example.biz.StableReleaseCodeBiz;
import com.example.biz.TestingExampleBiz;
import com.example.pojo.StableReleaseCode;
import com.example.pojo.TestingExample;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("accurateTesting")
public class AccurateTestingController {
	@Autowired
	private TestingExampleBiz testingExampleBiz;
	@Autowired
	private StableReleaseCodeBiz stableReleaseCodeBiz;
	/**
	 * 精准测试项目首页
	 * @return
	 */
	@SecurityIgnoreHandler
	@RequestMapping("index")
	public String index() {
		return "at_index";
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
		List<TestingExample> page = testingExampleBiz.page(pageNumber, pageSize, search);
		Integer total = testingExampleBiz.findTotal(search);
		json.put("rows", page);
		json.put("total", total);
		return json;
	}
	/**
	 * 获取测试用例关联的方法
	 * @param id 测试用例主键
	 * @return
	 */
	@SecurityIgnoreHandler
	@RequestMapping("getLinkMethod")
	@ResponseBody
	public JSONObject getLinkMethod(Integer id) {
		JSONObject json = new JSONObject();
		List<StableReleaseCode> list = stableReleaseCodeBiz.findByTestingExampleId(id);
		json.put("list", list);
		return json;
	}
}































