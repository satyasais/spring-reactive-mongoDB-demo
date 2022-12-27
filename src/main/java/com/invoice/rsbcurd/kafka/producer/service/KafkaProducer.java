package com.invoice.rsbcurd.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.invoice.rsbcurd.constants.ApiConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducer {
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	  public void sendMessage(String message){
	        log.info("Message sent -> %s", message);
	        kafkaTemplate.send(ApiConstants.TOPIC_NAME, message);
	    }

}
