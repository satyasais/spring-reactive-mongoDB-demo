package com.invoice.rsbcurd.kafkatest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.invoice.rsbcurd.kafka.consumer.KafkaConsumer;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class KafkaConsumerTest {
	
	@InjectMocks
	private KafkaConsumer kafkaConsumer;
	
	@Test
	void kafkaConsumerTest() {
		kafkaConsumer.consume("test");
	}

}
