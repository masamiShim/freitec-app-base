package com.freitech.kotetsu.config.jms;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.freitech.kotetsu.db.repositories.InformationRepository;

public class ScheduledPrducerTask {
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	InformationRepository informationRepository;

	@Scheduled(fixedDelay = 10000)
	public void sendMessage() {
		rabbitTemplate.convertAndSend(informationRepository.findAll());
	}
}
