package com.invoice.rsbcurd.reactive.controller;

import java.net.URI;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.rsbcurd.constants.ApiConstants;
import com.invoice.rsbcurd.swagger.SwaggerApiResponses;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Mono;

@RestController
public class IndexController implements ErrorController {

	@Operation(summary = ApiConstants.ERROR_PAGE, description = ApiConstants.API_FOR_ERROR_PAGE)
	@SwaggerApiResponses
	@GetMapping(ApiConstants.PATH)
	public String getErrorPath() {
		return ApiConstants.NO_MAPPING_FOUND;
	}

	@Operation(summary = ApiConstants.REDIRECTED_URL, description = ApiConstants.API_FOR_REDIRECTED_URL)
	@SwaggerApiResponses
	@GetMapping(ApiConstants.BACKWARD)
	public Mono<Void> indexController(ServerHttpResponse response) {
		response.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
		response.getHeaders().setLocation(URI.create(ApiConstants.PATH));
		return response.setComplete();
	}

}
