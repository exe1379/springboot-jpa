package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
Table name: users
+---------+-----------+-----------+---------+-----------+-------------+
| user_id | user_name | password_hash |  salt 	| 	email 		| role|
+---------+-----------+-----------+---------+-----------+-------------+
|  1      |  ann      | 123abc!@#  	  |  $123!# | 123@abc.com   |admin|
+---------+-----------+-----------+---------+-----------+-------------+
|  2      |  sean     | 123abc!@#  	  |  $123!# | 123@abc.com   | user|
+---------+-----------+-----------+---------+-----------+-------------+

*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String username;
	
	@Column(name = "password_hash" , nullable = false)
	private String passwordHash;
	
	@Column(name = "salt" , nullable = false)
	private String salt;
	
	@Column(name ="email", nullable = false)
	private String email;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "role")
	private String role;
}
