package com.freitech.kotetsu.config.jms;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;

//@Configuration
//@EnableScheduling
public class ProducerConfig {
	String queueName = "sample_queue";
		protected final String EXCHANGE_NAME = "sample.exchange";

	@Bean
	public Queue queue() {
		return new Queue(this.queueName);

	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(this.queueName);
	}


	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate.setRoutingKey(this.queueName);
		return rabbitTemplate;
	}
	
	@Bean
	public ScheduledPrducerTask scheduledPrducerTask() {
		return new ScheduledPrducerTask();
	}
	
	@Bean 
	HelloworldHandler helloworldHandler() {
		return new HelloworldHandler();
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueueNames(this.queueName);
		container.setMessageListener(listenerAdapter(helloworldHandler()));
		return container;		
		
	}

  @Bean
  MessageListenerAdapter listenerAdapter(HelloworldHandler handler) {
      return new MessageListenerAdapter(handler, "handleMessage");
  }

}
