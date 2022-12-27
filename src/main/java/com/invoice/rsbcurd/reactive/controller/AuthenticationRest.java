package com.invoice.rsbcurd.reactive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.rsbcurd.config.JWTUtil;
import com.invoice.rsbcurd.config.PBKDF2Encoder;
import com.invoice.rsbcurd.constants.ApiConstants;
import com.invoice.rsbcurd.reactive.model.AuthRequest;
import com.invoice.rsbcurd.reactive.model.AuthResponse;
import com.invoice.rsbcurd.reactive.service.UserService;
import com.invoice.rsbcurd.swagger.SwaggerApiResponses;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AuthenticationRest {
	
	    private JWTUtil jwtUtil;
	    private PBKDF2Encoder passwordEncoder;
	    private UserService userService;
	    @Operation(summary = ApiConstants.LOGIN_PAGE, description = ApiConstants.API_FOR_LOGIN_PAGE)
		@SwaggerApiResponses
	    @PostMapping(ApiConstants.LOGIN)
	    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
	        return userService.findByUsername(ar.getUsername())
	            .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
	            .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
	            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
	    }

}
