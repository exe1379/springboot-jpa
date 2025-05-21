package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<UserDto> findAllUsers() {
		return userRepository.findAll()
							 .stream()
							 .map(userMapper::toDto)
							 .toList();
	}

	@Override
	public UserDto getUser(String username) {
		User user = userRepository.findByUserName(username);
		return null;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(UserDto userDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addUser(String username, String password, String email, boolean active, String role) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(Integer userId, UserDto userDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(String username, String password, String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

	}

}
