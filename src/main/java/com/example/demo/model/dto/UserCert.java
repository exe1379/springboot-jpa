package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

//登入成功後會得到的資料，只有 Getter
@AllArgsConstructor
@ToString
@Getter
public class UserCert {
	private Integer userId;
	private String username;
	private String role;
}
