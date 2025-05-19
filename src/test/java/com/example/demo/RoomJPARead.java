package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepository;
@SpringBootTest
public class RoomJPARead {
	@Autowired
	private RoomRepository roomrepository;
	@Test
	public void test() {
		List<Room> rooms = roomrepository.findByRoomSizeGreaterThan(2);
		System.out.print(rooms);
	}
}
