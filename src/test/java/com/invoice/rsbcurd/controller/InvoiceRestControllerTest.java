package com.invoice.rsbcurd.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.invoice.rsbcurd.config.AuthenticationManager;
import com.invoice.rsbcurd.config.JWTUtil;
import com.invoice.rsbcurd.config.KafkaConfig;
import com.invoice.rsbcurd.config.SecurityContextRepository;
import com.invoice.rsbcurd.constants.ApiConstants;
import com.invoice.rsbcurd.kafka.producer.service.KafkaProducer;
import com.invoice.rsbcurd.reactive.controller.InvoiceRestController;
import com.invoice.rsbcurd.reactive.model.Invoice;
import com.invoice.rsbcurd.reactive.model.User;
import com.invoice.rsbcurd.reactive.service.IInvoiceService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest(InvoiceRestController.class)
@Slf4j
class InvoiceRestControllerTest {
	
	
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private IInvoiceService service;
	
	@Mock
	private SecurityContextRepository SecurityContextRepository;
	
	@Mock
	private AuthenticationManager authenticationManager;
	
	String tokenObj = null;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Mock
	private KafkaProducer kafkaProducer;
	
	@Mock
	private KafkaConfig kafkaConfig;
	
	@Mock
	private Properties properties;
	
	@BeforeEach
	public void setUp(ApplicationContext context) {

		User userDetails = new User();
		userDetails.setUsername("user");
		userDetails.setPassword("user");
		tokenObj = jwtUtil.generateToken(userDetails);
		log.info(tokenObj);
	}
	
	@Test
	void saveOneInvoiceTest() {
		
		Mono<Invoice> invoice = Mono.just(new Invoice(1,"InvioceTest","Invioce001",Double.MAX_VALUE));
		Mockito.doNothing().when(kafkaProducer).sendMessage(Mockito.anyString());
		Mockito.when(service.saveInvoice(Mockito.any(Invoice.class))).thenReturn(invoice);
		
		
		webTestClient.post().uri(ApiConstants.INVOICE+ApiConstants.SAVE)
		.header("Authorization","Bearer "+tokenObj)
		.body(invoice,Invoice.class)
		.exchange().expectStatus().isOk().expectBody().jsonPath("$.id").isNotEmpty();
		

	}
	
	
	@Test
	void getAllInvoicesTest() {
		
		Flux<Invoice> invoice = Flux.just(new Invoice(1,"InvioceTest","Invioce001",Double.MAX_VALUE),
				new Invoice(2,"InvioceTest1","Invioce002",Double.MAX_VALUE));
		
		Mockito.when(service.findAllInvoices()).thenReturn(invoice);
		
		Flux<Invoice> responseBodyObj = webTestClient.get().uri(ApiConstants.INVOICE+ApiConstants.ALL_INVOICES).header("Authorization","Bearer "+tokenObj)
		.exchange().expectStatus().isOk().returnResult(Invoice.class).getResponseBody();
		
		StepVerifier.create(responseBodyObj).expectSubscription()
		.expectNext(new Invoice(1,"InvioceTest","Invioce001",Double.MAX_VALUE))
		.expectNext(new Invoice(2,"InvioceTest1","Invioce002",Double.MAX_VALUE))
		.verifyComplete();

	}
	
	
	@Test
	void getOneInvoiceTest() {
		
		Mono<Invoice> invoice = Mono.just(new Invoice(1,"InvioceTest","Invioce001",Double.MAX_VALUE));
		
		Mockito.when(service.getOneInvoice(anyInt())).thenReturn(invoice);
		
		webTestClient.get().uri(ApiConstants.INVOICE+ApiConstants.GET_ID,1).header("Authorization","Bearer "+tokenObj)
				.exchange().expectStatus().isOk().expectBody()
				.jsonPath("$.name").isNotEmpty();		

	}
	
	
	@Test
	void deleteInvoiceTest() {
		
		Mockito.when(service.deleteInvoice(anyInt())).thenReturn(Mono.empty());
		
		Flux<String> restult = webTestClient.delete().uri(ApiConstants.INVOICE+ApiConstants.DELETE_ID,2).header("Authorization","Bearer "+tokenObj)
				.exchange().expectStatus().isOk().returnResult(String.class).getResponseBody();
		assertEquals(ApiConstants.INVOICE_WITH_ID +2+ ApiConstants.DELETED, restult.blockLast());
	}
	
	
}
