package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
	private Integer status;
	private String message;
	T data;
	public static <T> ApiResponse<T> success(String message , T data){
		return new ApiResponse<T>(200 ,message, data);
	}
	public static <T> ApiResponse<T> error(int status, String message){
		return new ApiResponse<T>(status, message, null);
	}
}
