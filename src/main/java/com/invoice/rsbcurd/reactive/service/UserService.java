package com.invoice.rsbcurd.reactive.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.invoice.rsbcurd.reactive.model.Role;
import com.invoice.rsbcurd.reactive.model.User;

import reactor.core.publisher.Mono;

@Service
public class UserService {
	
	private Map<String, User> data;

    @PostConstruct
    public void init() {
        data = new HashMap<>();

        data.put("user", new User("user", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", true, Arrays.asList(Role.ROLE_USER)));

        data.put("admin", new User("admin", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", true, Arrays.asList(Role.ROLE_ADMIN)));
    }

    public Mono<User> findByUsername(String username) {
        return Mono.justOrEmpty(data.get(username));
    }

}
