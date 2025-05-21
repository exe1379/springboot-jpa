package com.example.demo.model.dto;

// 屬性名稱可與對應 entity 不同
public class UserDto {
	// 前端不需要放 salt 與 password 因此不需要放
	private Integer userId;
	private String username;
	private String email;
	private Boolean active;
	private String role;
}
