package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.RoomAlreadyExistException;
import com.example.demo.Exception.RoomNotFoundException;
import com.example.demo.method.RoomMapper;
import com.example.demo.model.dto.RoomDto;
import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.RoomService;
@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private RoomMapper roomMapper;
	
	@Override
	public List<RoomDto> findAllRooms() {
		return roomRepository.findAll()
							 .stream()
							 .map(roomMapper::toDto)
							 .toList();
	}

	@Override
	public RoomDto getRoomById(Integer roomId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException("找不到該房間"));
		return roomMapper.toDto(room);
	}

	@Override
	public void addRoom(RoomDto roomDto) {
		// 先判斷該房號是否已存在
		Optional<Room> optRoom = roomRepository.findById(roomDto.getRoomId());
		// 若存在則拋出例外
		if(optRoom.isPresent()) {
			throw new RoomAlreadyExistException("新增失敗: 房號 " + roomDto.getRoomId() + " 已經存在");
		}
		// 不存在則建立一個新的 Room 物件(RoomDto 轉換而成)
		Room room = roomMapper.toEntity(roomDto);
		roomRepository.save(room);
	}

	@Override
	public void addRoom(Integer roomId, String roomName, Integer roomSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRoom(Integer roomId, RoomDto roomDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRoom(Integer roomId, String roomName, Integer roomSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRoom(Integer roomId) {
		// TODO Auto-generated method stub

	}

}
