package com.example.demo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "is_enabled")
	private boolean isEnabled;
	@Column(name = "is_admin")
	private boolean isAdmin;

	public User(String username, String password, boolean isAdmin) {
		super();
		this.username = username;
		this.password = password;
		this.isEnabled = true;
		this.isAdmin = isAdmin;
	}

}
