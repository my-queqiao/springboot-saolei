package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.biz.UserBiz;
import com.example.pojo.Liuyan;
import com.example.pojo.Users;
@Controller
public class Helloworld{
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private RedisTemplate<String, Object> template;
	
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
		list.add(new User(2, "张三", 30));
		list.add(new User(3, "张三", 40));
		model.addAttribute("userList", list);
		System.out.println("dffdfdfd_________22323");
		return "user";
	}
	@RequestMapping("userList")
	public String userList(Model model) {
		List<Users> findAll = userBiz.findAll();
		model.addAttribute("userList", findAll);
		return "userList";
	}
	@RequestMapping("redis")
	@ResponseBody
	public Map<String, String> t() {
		Map<String,String> map = new HashMap<>();
		try {
			ValueOperations<String, Object> opsForValue = template.opsForValue();
			opsForValue.set("spring", "for you");
			String s = (String)opsForValue.get("spring");
			System.out.println("s:"+s);
			//存储Java实例
			User user = new User(1, "lizhao", 33);
			template.setValueSerializer(new JdkSerializationRedisSerializer());//重新设置序列化方式，Java对象的序列化。
			opsForValue.set("user", user);
			User u = (User)opsForValue.get("user");
			System.out.println("取出之后："+u.toString());
		} catch (Exception e) {
			System.out.println("出错:"+e);
		}
		map.put("a", "b");
		return map;
	}
}
