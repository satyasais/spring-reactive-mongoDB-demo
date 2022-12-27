package com.invoice.rsbcurd.reactive.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.invoice.rsbcurd.reactive.model.User;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	
	@Test
	void findByUsernameTest() {
		
		User user = new User();
		user.setPassword("user");
		user.setUsername("user");
		Mono<User> userMonoObj = Mono.just(user);
		userService.init();
		Mono<User> result = userService.findByUsername("user");
		
		assertEquals(userMonoObj.block().getUsername(), result.block().getUsername());
		
	}

}
