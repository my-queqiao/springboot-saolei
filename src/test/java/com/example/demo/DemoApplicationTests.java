package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.biz.LiuyanBiz;
import com.example.pojo.Liuyan;
import com.example.rabbitmq.exchange.direct.ErrorSender2;
import com.example.rabbitmq.exchange.direct.InfoSender2;
import com.example.rabbitmq.exchange.fanout.OrderSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	private LiuyanBiz liuyanBiz;
	@Autowired
	private RedisTemplate<String, Object> template;
	@Autowired
	private OrderSender sender;
	//@Test
	public void contextLoads() {
		Liuyan ly = new Liuyan();
		ly.setLiuyan("spring for you2");
		liuyanBiz.save(ly);
	}
	//@Test
	public void t() {
		System.out.println("-------------------------------------------------分割线");
		template.opsForValue().set("spring", "for you");
		String s = (String)template.opsForValue().get("spring");
		System.out.println("s:"+s);
	}
	@Test
	public void a() {
		sender.send("spring for you3333333333333");
	}
}
