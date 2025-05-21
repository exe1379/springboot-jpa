package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.PasswordInvalidException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.model.dto.UserCert;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CertService;
import com.example.demo.util.Hash;
@Service
public class CertServiceImpl implements CertService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserCert getCert(String username, String password) throws UserNotFoundException, PasswordInvalidException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UserNotFoundException("查無此人");
		}
		String hassPassword = Hash.getHash(password, user.getSalt());
		if(!hassPassword.equals(user.getPasswordHash())){
			throw new PasswordInvalidException("密碼錯誤");
		}
		UserCert userCert = new UserCert(user.getUserId(),user.getUsername(),user.getRole());
		return userCert;
	}
}
