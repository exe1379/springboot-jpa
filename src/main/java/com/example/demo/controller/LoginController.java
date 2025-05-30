package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Exception.CertException;
import com.example.demo.model.dto.UserCert;
import com.example.demo.service.CertService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private CertService certService;
	
	@GetMapping
	public String login() {
		return("/login");
	}
	
	@PostMapping
	public String checkLogin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
		UserCert userCert = null;
		try {
			userCert = certService.getCert(username, password);
		}catch(CertException e) {
			session.invalidate();
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			return "err";
		}
		session.setAttribute("userCert", userCert);
		return "redirect:/room";
	}
}
