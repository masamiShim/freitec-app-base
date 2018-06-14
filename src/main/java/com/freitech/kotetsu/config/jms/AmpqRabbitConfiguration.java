package com.freitech.kotetsu.config.jms;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

//@Configuration
public class AmpqRabbitConfiguration {
	protected final String QUEUE_NAME = "mail.message.queue";
	protected final String EXCHANGE_NAME = "mail.message.exchange";

	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME);

	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME);
	}
	
//	@Bean 
//	HelloworldHandler helloworldHandler() {
//		return new HelloworldHandler();
//	}
//
//	@Bean
//	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//		container.setQueueNames(QUEUE_NAME);
//		container.setMessageListener(listenerAdapter(helloworldHandler()));
//		return container;		
//		
//	}
//
//  @Bean
//  MessageListenerAdapter listenerAdapter(HelloworldHandler handler) {
//      return new MessageListenerAdapter(handler, "handleMessage");
//  }

//  @Bean
//  MessageListenerAdapter listenerAdapter(Receiver receiver) {
//      return new MessageListenerAdapter(receiver, "receiveMessage");
//  }


	@Bean
	public ScheduledPrducerTask scheduledPrducerTask() {
		return new ScheduledPrducerTask();
	}


}
