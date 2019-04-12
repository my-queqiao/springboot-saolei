package com.example.rabbitmq.exchange.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * user服务器的消息发送者
 * @author tom
 *
 */
//@Component
public class UserSender {
	@Autowired
	private AmqpTemplate amqpTemplate;
	@Value("${mq.config.exchange}")
	private String exchange;
//	@Value("${mq.config.queue.info.routing.key}")
//	private String routingKey;
	
	public void send(String msg) {
		//发送给哪个交换器下的，哪些?哪个？路由键（路由键指向队列）
		amqpTemplate.convertAndSend(exchange,"user.log.info","user.log.info:"+msg);//日志的四种类型
		amqpTemplate.convertAndSend(exchange,"user.log.error","user.log.error:"+msg);
		amqpTemplate.convertAndSend(exchange,"user.log.warn","user.log.warn:"+msg);
		amqpTemplate.convertAndSend(exchange,"user.log.debug","user.log.debug:"+msg);
	}
}
