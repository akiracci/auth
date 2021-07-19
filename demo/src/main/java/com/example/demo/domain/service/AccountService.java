package com.example.demo.domain.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.Account;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;

/*
 * https://spring.pleiades.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/UserDetailsService.html
 */
@Service
public class AccountService implements UserDetailsService {

	@Autowired
	UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username == null || "".equals(username)) {
			throw new UsernameNotFoundException("Username is empty");
		}

		User user = repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		}

		if (!user.isEnabled()) {
			throw new UsernameNotFoundException("User is disable: " + username);
		}

		Account acount = new Account(user, getAuthorities(user));

		return acount;
	}

	private Collection<GrantedAuthority> getAuthorities(User user) {

		if (user.isAdmin()) {
			return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
		} else {
			return AuthorityUtils.createAuthorityList("ROLE_USER");
		}

	}

	@Transactional
	public void registerAdmin(String username, String password) {
		User user = new User(username, passwordEncoder.encode(password), true);
		repository.save(user);
	}

	@Transactional
	public void registerUser(String username, String password) {
		User user = new User(username, passwordEncoder.encode(password), false);
		repository.save(user);
	}

}
