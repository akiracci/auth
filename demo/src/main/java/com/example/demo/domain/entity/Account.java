package com.example.demo.domain.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/*
 * https://spring.pleiades.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/UserDetails.html
 */
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails {

	private static final long serialVersionUID = 1L;

	private User user;
	private Collection<GrantedAuthority> grantedAuthority;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthority;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	public boolean isAdmin() {
		return user.isAdmin();
	}
}
