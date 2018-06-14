package com.freitech.kotetsu.config.jms;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.freitech.kotetsu.models.information.Information;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Comsumer {
	
	@RabbitListener(queues = "sample_queue")
	public void consumer(List<Information> list) {
		log.warn("これこれ > " + list.toString());
	}
}
