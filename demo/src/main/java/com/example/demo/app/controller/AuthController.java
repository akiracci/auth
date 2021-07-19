package com.example.demo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.form.SignupForm;
import com.example.demo.domain.service.AccountService;

@Controller
@RequestMapping("/")
public class AuthController {

	/**
	 * アカウントサービス
	 */
	@Autowired
	private AccountService service;

	/**
	 * サインアップフォーム
	 * @param form
	 * @param model
	 * @return
	 */
	@GetMapping("/signup")
	public String signup(@ModelAttribute SignupForm form, Model model) {
		return "signup";
	}

	/**
	 * サインアップ登録
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/signup")
	public String regist(@Validated @ModelAttribute SignupForm form, BindingResult result, Model model) {
		if (result.hasErrors())
			return signup(form, model);
		service.registerAdmin(form.getUsername(), form.getPassword());
		return "redirect:/login";
	}

	/**
	 * 認証ページ
	 * @param model
	 * @return
	 */
	@GetMapping("/members")
	public String memberIndex(@AuthenticationPrincipal User user) {
		return "members/index";
	}
}
