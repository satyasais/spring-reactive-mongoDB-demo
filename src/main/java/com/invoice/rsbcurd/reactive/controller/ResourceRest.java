package com.invoice.rsbcurd.reactive.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.rsbcurd.constants.ApiConstants;
import com.invoice.rsbcurd.reactive.model.Message;
import com.invoice.rsbcurd.swagger.SwaggerApiResponses;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Mono;

@RestController
public class ResourceRest {

	@Operation(summary = ApiConstants.USER_PAGE, description = ApiConstants.API_FOR_USER_PAGE)
	@SwaggerApiResponses
	@GetMapping(ApiConstants.RESOURCE_USER)
	@PreAuthorize(ApiConstants.HAS_ROLE_USER)
	public Mono<ResponseEntity<Message>> user() {
		return Mono.just(ResponseEntity.ok(new Message(ApiConstants.CONTENT_FOR_USER)));
	}

	@Operation(summary = ApiConstants.ADMIN_PAGE, description = ApiConstants.API_FOR_ADMIN_PAGE)
	@SwaggerApiResponses
	@GetMapping(ApiConstants.RESOURCE_ADMIN)
	@PreAuthorize(ApiConstants.HAS_ROLE_ADMIN)
	public Mono<ResponseEntity<Message>> admin() {
		return Mono.just(ResponseEntity.ok(new Message(ApiConstants.CONTENT_FOR_ADMIN)));
	}

	@Operation(summary = ApiConstants.API_FOR_ADMIN_PAGE, description = ApiConstants.API_FOR_ADMIN_OR_USER_PAGE)
	@SwaggerApiResponses
	@GetMapping(ApiConstants.RESOURCE_USER_OR_ADMIN)
	@PreAuthorize(ApiConstants.HAS_ROLE_USER_OR_HAS_ROLE_ADMIN)
	public Mono<ResponseEntity<Message>> userOrAdmin() {
		return Mono.just(ResponseEntity.ok(new Message(ApiConstants.CONTENT_FOR_USER_OR_ADMIN)));
	}

}
