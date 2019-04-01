package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.biz.UserBiz;
import com.example.pojo.Users;
@Controller
public class Helloworld{
	@Autowired
	private UserBiz userBiz;
	
	@RequestMapping("fileUploadController")
	@ResponseBody
	public Map<String, String> fileUploadController(MultipartFile filename) throws IllegalStateException, IOException {
		System.out.println(filename);
		System.out.println(filename.getOriginalFilename());
		filename.transferTo(new File("C:\\Users\\tom\\Desktop\\"+filename.getOriginalFilename()));
		Map<String,String> map = new HashMap<String,String>();
		map.put("msg", "spring for you");
		return map;
	}
	/*
	 * 如果使用freemarker试图技术，则需要在src/main/resources下建立templates文件夹，再在其中建.ftl文件
	 * freemarker和整合jsp时不同，不需要在application.properties配置文件中说明视图文件路径的前后缀。 
	 * @param model
	 * @return
	 */
	@RequestMapping("user")
	public String user(Model model) {
		List<User> list = new ArrayList<>();
		list.add(new User(1, "张三", 20));
		list.add(new User(2, "张三", 20));
		list.add(new User(3, "张三", 20));
		model.addAttribute("userList", list);
		return "user";
	}
	@RequestMapping("userList")
	public String userList(Model model) {
		List<Users> findAll = userBiz.findAll();
		model.addAttribute("userList", findAll);
		return "userList";
	}
}
