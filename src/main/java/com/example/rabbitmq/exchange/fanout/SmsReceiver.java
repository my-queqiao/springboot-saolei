package com.example.rabbitmq.exchange.fanout;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 短信服务接收者
 * @author tom
 *
 */
//@Component
@RabbitListener(
		bindings=@QueueBinding(
				value=@Queue(value="${mq.config.queue.sms}"),
				exchange=@Exchange(value="${mq.config.exchange}",type=ExchangeTypes.FANOUT)
				) //广播模式向该交换器下所有队列发送消息，不需要路由键
		)
public class SmsReceiver {
	int a = 0;
	@RabbitHandler //配合上面的类的注解，由该方法接收消息
	public void receive(String msg) {
		
		System.out.println("sms,receiver"+msg+"次数："+a++);
		double c = 1/0;
		try {
			double b = 1/0; //有异常，rabbitmq将收不到ack确认，但是try catch异常之后，会返回ack确认。
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
