package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.UserAlreadyExistException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.Hash;
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
		User user = userRepository.findByUsername(username);
		if(user == null) {
			return null;
		}
		return userMapper.toDto(user);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(UserDto userDto) {
		Optional<User> optUser = userRepository.findById(userDto.getUserId());
		if(optUser.isPresent()) {
			throw new UserAlreadyExistException("用戶名已存在");
		}
		User user = userMapper.toEntity(userDto);
		userRepository.save(user);
	}

	@Override
	public void addUser(String username, String password, String email, boolean active, String role) {
		String salt = Hash.getSalt();
		String passwordHash = Hash.getHash(password, salt);
		User user = new User(null, username, passwordHash, salt, email, active, role);
		userRepository.save(user);
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
		Optional<User> optUser = userRepository.findById(userId);
		if(optUser.isEmpty()) {
			throw new UserNotFoundException("使用者不存在");
		}
		userRepository.deleteById(userId);;
	}

}
