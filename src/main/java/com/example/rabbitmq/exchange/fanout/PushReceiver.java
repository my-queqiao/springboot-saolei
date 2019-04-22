package com.example.rabbitmq.exchange.fanout;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 	服务接收者(具体的接收方法，一个方法只能从一个队列中接收消息。 如果一个交换器绑定了多个队列，那么每一个队列都需要指定一个独立的方法接收。 )
 * @author tom
 *
 */
//@Component
@RabbitListener(
		bindings=@QueueBinding(
				value=@Queue(value="${mq.config.queue.push}"),
				exchange=@Exchange(value="${mq.config.exchange}",type=ExchangeTypes.FANOUT)
				) //广播模式向该交换器下所有队列发送消息，不需要路由键
		)
public class PushReceiver {
	@RabbitHandler //配合上面的类的注解，由该方法接收消息
	public void receive(String msg) {
		System.out.println("push,receiver"+msg);
	}
}
