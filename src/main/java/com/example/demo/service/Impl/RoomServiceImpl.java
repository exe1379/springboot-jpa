package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.RoomAlreadyExistException;
import com.example.demo.Exception.RoomNotFoundException;
import com.example.demo.mapper.RoomMapper;
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
		RoomDto roomDto = new RoomDto(roomId, roomName, roomSize);
		addRoom(roomDto);
	}

	@Override
	public void updateRoom(Integer roomId, RoomDto roomDto) {
		// 判斷該房號是否已經存在 ?
				Optional<Room> optRoom = roomRepository.findById(roomId);
				if(optRoom.isEmpty()) { // 房間不存在
					throw new RoomAlreadyExistException("修改失敗: 房號 " + roomId + " 不存在");
				}
				roomDto.setRoomId(roomId);
				Room room = roomMapper.toEntity(roomDto);
				roomRepository.saveAndFlush(room); // 更新(馬上強制寫入更新)
				//roomRepository.save(room); // 更新(可以配合交易模式, 若交易失敗則會回滾)
	}

	@Override
	public void updateRoom(Integer roomId, String roomName, Integer roomSize) {
		RoomDto roomDto = new RoomDto(roomId, roomName, roomSize);
		updateRoom(roomId, roomDto);
	}

	@Override
	public void deleteRoom(Integer roomId) {
		// 判斷該房號是否已經存在 ?
				Optional<Room> optRoom = roomRepository.findById(roomId);
				if(optRoom.isEmpty()) { // 房間不存在
					throw new RoomAlreadyExistException("刪除失敗: 房號 " + roomId + " 不存在");
				}
		roomRepository.deleteById(roomId);
	}
}
