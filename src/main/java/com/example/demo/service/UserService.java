package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.UserDto;

public interface UserService {
	public List<UserDto> findAllUsers();
	public UserDto getUser(String username);
	public UserDto getUserById(Integer userId);
	public void addUser(UserDto userDto);
	public void addUser(String username, String password, String email, boolean active , String role);
	public void updateUser(Integer userId, UserDto userDto);
	public void updateUser(String username, String password, String email);
	public void deleteUser(Integer userId);
}
