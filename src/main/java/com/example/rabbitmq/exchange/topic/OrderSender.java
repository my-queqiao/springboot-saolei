package com.example.rabbitmq.exchange.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 订单服务器的消息发送者
 * @author tom
 *
 */
//@Component
public class OrderSender {
	@Autowired
	private AmqpTemplate amqpTemplate;
	@Value("${mq.config.exchange}")
	private String exchange;
//	@Value("${mq.config.queue.info.routing.key}")
//	private String routingKey;
	
	public void send(String msg) {
		//发送给哪个交换器下的，哪些?哪个？路由键（路由键指向队列）
		amqpTemplate.convertAndSend(exchange,"order.log.info","order.log.info:"+msg);//日志的四种类型
		amqpTemplate.convertAndSend(exchange,"order.log.error","order.log.error:"+msg);
		amqpTemplate.convertAndSend(exchange,"order.log.warn","order.log.warn:"+msg);
		amqpTemplate.convertAndSend(exchange,"order.log.debug","order.log.debug:"+msg);
	}
}
