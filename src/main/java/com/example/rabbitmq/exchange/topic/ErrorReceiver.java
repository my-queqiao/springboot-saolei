package com.example.rabbitmq.exchange.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/**
 * 	消息接收者(只接收错误消息，不管是哪个服务的)
 * 理解：消息发送者：指定转换器、路由键
 * 	        消息接收者：指定转换器、路由键、队列名称
 * 	   消息先发送到转换器，再由转换器发送到队列，转换器和队列基于路由键绑定在一起。
 * @author tom
 *
 */
//@Component
@RabbitListener(
		//bindings是@RabbitListener的一个字段属性（类型是@QueueBinding），等号右边显然是为该字段赋值。注解不用new
		bindings=@QueueBinding( 
				value=@Queue(value="${mq.config.queue.error}",autoDelete="true"),
				exchange=@Exchange(value="${mq.config.exchange}",type=ExchangeTypes.TOPIC),
				key= {"*.log.error"}//key是数组类型
				)
		)
public class ErrorReceiver {
	/**
	 * 	接收消息的方法，采用消息队列监听机制
	 */
	@RabbitHandler 
	public void process(String msg) {
		System.out.println("error,Receiver:"+msg);
	}
}
