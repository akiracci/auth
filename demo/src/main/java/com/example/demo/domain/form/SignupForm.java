package com.example.demo.domain.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignupForm {
	@Email
	private String username;
	@Size(min = 6, max = 24)
	private String password;
}
