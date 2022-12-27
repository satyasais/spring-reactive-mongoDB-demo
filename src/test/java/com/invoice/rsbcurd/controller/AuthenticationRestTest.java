package com.invoice.rsbcurd.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.invoice.rsbcurd.config.JWTUtil;
import com.invoice.rsbcurd.config.PBKDF2Encoder;
import com.invoice.rsbcurd.constants.ApiConstants;
import com.invoice.rsbcurd.reactive.controller.AuthenticationRest;
import com.invoice.rsbcurd.reactive.model.AuthRequest;
import com.invoice.rsbcurd.reactive.model.User;
import com.invoice.rsbcurd.reactive.repo.InvoiceRepository;
import com.invoice.rsbcurd.reactive.service.UserService;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(AuthenticationRest.class)
class AuthenticationRestTest {
	
	
	private static final String TOKEN = "$.token";

	private static final String USER = "user";

	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private InvoiceRepository invoiceRepository;
	
	@MockBean
	private PBKDF2Encoder passwordEncoder;
	
	@MockBean
	private JWTUtil jwtUtil;
	
	
	@BeforeEach
	public void setUp() {
	    webTestClient = webTestClient.mutate()
	                                 .responseTimeout(Duration.ofMillis(50000))
	                                 .build();
	}
	
	
	@Test
	void loginTest() {
		
		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername(USER);
		authRequest.setPassword(USER);
		User userObj =new User();
		userObj.setUsername(USER);
		userObj.setPassword(USER);
		Mockito.when(userService.findByUsername(anyString())).thenReturn(Mono.just(userObj));
		Mockito.when(passwordEncoder.encode(anyString())).thenReturn(userObj.getPassword());
		Mockito.when(jwtUtil.generateToken(any(User.class))).thenReturn(userObj.getPassword());
		
		webTestClient.post().uri(ApiConstants.LOGIN)
		.body(Mono.just(authRequest),AuthRequest.class)
		.accept(MediaType.APPLICATION_JSON)
		.exchange().expectStatus().isOk().expectBody().jsonPath(TOKEN).isNotEmpty();

	}

}
