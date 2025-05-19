package com.example.demo.model.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
	@Min(value=1)
	@NotNull(message = "{roomDto.roomId.notNull}")
	private Integer roomId;
	@NotNull(message = "{roomDto.roomName.notNull}")
	@Size(min = 2, message = "{roomDto.roomName.Size}")
	private String roomName;
	@NotNull(message = "{roomDto.roomSize.notNull}")
	@Range(min = 1, max = 500 , message ="{roomDto.roomSize.Range}")
	private Integer roomSize;
}
