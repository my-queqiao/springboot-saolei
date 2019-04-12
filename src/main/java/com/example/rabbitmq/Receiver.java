package com.example.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	/**
	 *	监听该队列，就是从该队列中获取消息，
	 *	1、当然每一个消息只能获取一次，这和所有的队列数据结构一样。
	 *	2、在本项目中，消息发送、接收都在本项目中。当然可以在本项目中只发送，然后在另外一个项目中接收。当然需要两个项目使用同一个rabbitmq服务。
	 *	3、我理解的部分原理：
	 *		spring操作rabbitmq创建（名称，队列实例），这也会存放到rabbitmq服务中，
	 *		接收消息时，spring会到rabbitmq服务中查询该队列，并获取消息。
	 *		至于，接收方法为什么能够在队列一有消息就立即能够获取出来，怎么实现的？还不清楚。用递归轮询队列肯定不合适。  
	 */
	@RabbitListener(queues= {"hello-queue"}) //监听该队列，可以监听多个队列。queues是该注解的一个字段。
	public void receive(String msg) {
		System.out.println("接收消息："+msg);
	}
}
