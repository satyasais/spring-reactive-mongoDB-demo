package com.invoice.rsbcurd.controller;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.invoice.rsbcurd.constants.ApiConstants;
import com.invoice.rsbcurd.reactive.controller.ResourceRest;
import com.invoice.rsbcurd.reactive.repo.InvoiceRepository;

@RunWith(SpringRunner.class)
@WebFluxTest(ResourceRest.class)
@ContextConfiguration
class ResourceRestTest {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private InvoiceRepository invoiceRepository;
	
	@MockBean
	private UserDetailsService userDetailsService;
	
	@BeforeEach
	public void setUp() {
	    webTestClient = webTestClient.mutate()
	                                 .responseTimeout(Duration.ofMillis(50000))
	                                 .build();
	}
	
	
	@Test
	@WithMockUser
	void userRoleTest() {
		
		webTestClient.get().uri(ApiConstants.RESOURCE_USER)
				.exchange().expectStatus().isOk().expectBody()
				.jsonPath("$.content").isNotEmpty();		
	}
	
	
	@Test
	@WithMockUser(username="admin",roles={"USER","ADMIN"})
	void adminRoleTest() {
		
		webTestClient.get().uri(ApiConstants.RESOURCE_ADMIN)
				.exchange().expectStatus().is2xxSuccessful();		
	}
	
	@Test
	@WithMockUser
	void userOrAdminRoleTest() {
		
		webTestClient.get().uri(ApiConstants.RESOURCE_USER_OR_ADMIN)
				.exchange().expectStatus().isOk();		
	}

}
