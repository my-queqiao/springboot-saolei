package com.example.rabbitmq.exchange.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 测试fanout扇出交换器，发送消息
 * @author tom
 *
 */
//@Component
public class OrderSender {
	@Value("${mq.config.exchange}")
	private String exchange;
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(String msg) {
		amqpTemplate.convertAndSend(exchange, "", msg);
	}
	
}
