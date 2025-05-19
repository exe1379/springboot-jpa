package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	// Spring 內建的轉換器，其他程式可通過 @Autowired來取用
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
