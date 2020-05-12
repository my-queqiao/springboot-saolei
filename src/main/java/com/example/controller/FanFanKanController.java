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
public class FanFanKanController {
	Logger logger = LoggerFactory.getLogger(getClass());
	// 1、成员字段线程不安全
	// 2、当前类有spring实例化，是单例的，上次访问之后，以下成员字段赋值了，后面访问时，还会保留上次的值（因为当前类大概只实例化一次，也只有一个实例）
//	Queue<String> queue = new LinkedList<>(); 
//	List<String> list = new ArrayList<>(20);
	/**每次刷新页面时
	 * 随机生成【1，10】之间的正整数，每个数字再复制一个，共20个数字，放到队列中。
	 */
	@SecurityIgnoreHandler
	@RequestMapping("fanfankan")
	public String fanfankan(HttpSession session){
		System.out.println("翻翻看"); // dev，测试版本差异
		/*// 先初始化，相当于每个线程中都new一个实例×，也可以用threadLocal修饰来实现
		queue = new LinkedList<>();
		list = new ArrayList<>(20);
		// （1）错误，这两行代码相当于没写，因为这两个字段还是成员变量，线程还是不安全。
		 * 	知识点：由于成员变量是所有线程共享的，所以后一个线程会覆盖上一个线程的赋值。
		 * 	
		 * 	（2）改用ThreadLocal来修饰这两个成员变量，再在当前方法中用set方法赋值。这一步没有问题。
		 * 	但是在getPicUrl方法中，用get方法获取值出错了，因为获取值的方法属于另外一个线程，而另外一个线程可能还没赋值，也可能赋的是其它的值
		 * 	知识点：线程本地类是把成员变量在每个线程中都复制一份，从而线程安全。在A线程赋的值就在A线程中使用。
		*/ 
		Queue<String> queue = new LinkedList<>(); 
		List<String> list = new ArrayList<>(20);
		create(list);
		Collections.shuffle(list); // 洗牌
		//Collections.shuffle(list,new Random(1L)); //如果指定种子则每次洗牌后的顺序都一样
//		for(String i:list) {
//			queue.offer(i); // offer先进，poll先出
//		}
		session.setAttribute("list", list); // 用户是唯一的
		//System.out.println("queue:"+queue);
		return "fanfankan";
	}
	public void create(List<String> list) {
		for(int i=1;i<=10;i++) {
			list.add(String.valueOf(i));
		}
		for(int i=1;i<=10;i++) {
			list.add(String.valueOf(i));
		}
	}
	@SecurityIgnoreHandler
	@RequestMapping("getPicUrl")
	@ResponseBody
	public JSONObject getPicUrl(HttpSession session){
		JSONObject json = new JSONObject();
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>)session.getAttribute("list");
		json.put("list", list);
		// 不使用队列了，改成一次刷新页面返回全部图片，这样生成图片url集合的方法也可以放到当前方法中，不用session了
		// 每次点击方块时，都从队列中返回一个图片url，这样实现，前端实现功能时更困难。
		// 如：点击方块时，还需要判断是否执行当前方法，大概由于请求当前方法是ajax请求，还有jquery提供的隐藏元素方法，可能也有同步、异步的问题
		// 出现了很多我解决不了的问题，详见ftl文件中的bug描述
//		String poll = queue.poll();
		return json;
	}
}
