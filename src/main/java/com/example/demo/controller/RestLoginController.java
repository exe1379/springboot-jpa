package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.CertException;
import com.example.demo.model.dto.LoginRequest;
import com.example.demo.model.dto.UserCert;
import com.example.demo.service.CertService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/login")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class RestLoginController {
	
	@Autowired
	private CertService certService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session){
		try {
			UserCert userCert = certService.getCert(request.getUsername(),request.getPassword());
			session.setAttribute("userCert", userCert);
			return ResponseEntity.ok(userCert);
		}catch(CertException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
