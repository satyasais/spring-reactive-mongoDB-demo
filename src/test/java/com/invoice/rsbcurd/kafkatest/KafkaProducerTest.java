package com.invoice.rsbcurd.kafkatest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.invoice.rsbcurd.kafka.producer.service.KafkaProducer;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class KafkaProducerTest {
	
	@InjectMocks
	private KafkaProducer kafkaProducer;
	
	@Mock
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Test
	void kafkaProducerTest() {
		kafkaProducer.sendMessage("test");
	}

}
