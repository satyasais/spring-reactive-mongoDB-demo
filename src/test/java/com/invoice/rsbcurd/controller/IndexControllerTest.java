package com.invoice.rsbcurd.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.mock.http.server.reactive.MockServerHttpResponse;

import com.invoice.rsbcurd.reactive.controller.IndexController;

import reactor.core.publisher.Mono;

@RunWith(MockitoJUnitRunner.class)
class IndexControllerTest {
	
	private static final  String TEST = "No Mapping Found";
	
	@InjectMocks
    private IndexController indexController = new IndexController();
	
	@Mock
	private ServerHttpResponse response = new MockServerHttpResponse();
	
	@Test
	void getErrorPathTest() throws Exception {
		String result1 = indexController.getErrorPath();
		Mono<Void> result2=	indexController.indexController(response);
       	
		assertEquals(TEST, result1);
		assertEquals(Mono.empty().block(), result2.block());
	}

}
