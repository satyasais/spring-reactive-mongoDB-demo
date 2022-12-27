package com.invoice.rsbcurd.reactive.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Generated
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
	
	 private static final long serialVersionUID = 1L;

	    private String username;

	    private String password;

	    private Boolean enabled;

	    private List<Role> roles;

	    @Override
	    public String getUsername() {
	        return username;
	    }


	    @Override
	    public boolean isAccountNonExpired() {
	        return false;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return false;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return false;
	    }

	    @Override
	    public boolean isEnabled() {
	        return this.enabled;
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return this.roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList());
	    }

	    @JsonIgnore
	    @Override
	    public String getPassword() {
	        return password;
	    }

}
