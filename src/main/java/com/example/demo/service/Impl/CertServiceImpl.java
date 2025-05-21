package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Exception.CertException;
import com.example.demo.model.dto.UserCert;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CertService;

public class CertServiceImpl implements CertService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserCert getCert(String username, String password) throws CertException {
		User user = userRepository.findByUserName(username);
		return null;
	}

}
