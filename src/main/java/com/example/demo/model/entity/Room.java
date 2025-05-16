package com.example.demo.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //代表這個類別是一個 Java 的實體類別，會與資料表進行對應(會自動建立資料表)
@Table(name = "room") // 若資料表名稱與類別名稱一致可省略此行
public class Room {
	@Id
	@Column(name = "room_id")
	private Integer roomId;
	@Column(name="room_name", nullable = false , unique = true)
	private String roomName;
	@Column(name="room_size", columnDefinition = "integer default 0")
	private Integer roomSize;
}
