package com.example.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 	创建消息队列    	
 * 	消息队列，是应用之间通信的一种方式，消息队列是消息的中转站。消息队列是一个独立的服务，专门负责收发消息，当然部署到哪个服务器自己决定
 * 	另一种常见方式是应用之间通过tcp、udp直接通信。
 * @author tom
 *
 */
@Configuration
public class RabbitmqConfig {
	@Bean
	public Queue createQueue() {
		/*
		 *  Queue是spring整合、操作rabbitmq的一个类，可以在rabbitmq服务中创建一个消息队列。
		 */
		return new Queue("hello-queue");
	}
}
