package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>{ // Room: entity, Integer : @Id 的型別
	
}
