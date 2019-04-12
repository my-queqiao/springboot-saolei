package com.example.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	@Autowired
	private AmqpTemplate amqpTemplate;
	public void send(String msg) {
		//向该消息队列发送一个消息msg
		amqpTemplate.convertAndSend("hello-queue",msg);//routingKey是消息队列的key
	}
}
