package com.invoice.rsbcurd.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;

import com.invoice.rsbcurd.constants.ApiConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaConsumer {

	@KafkaListener(topics = ApiConstants.TOPIC_NAME, groupId = ApiConstants.GROUP_ID)
	public void consume(String message) {
		log.info("message = " + message);
	}

}
