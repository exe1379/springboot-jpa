package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.RoomException;
import com.example.demo.model.dto.RoomDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.RoomService;

@RestController
@RequestMapping("/rest/room")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8002"}, allowCredentials = "true")
public class RoomRestController {
	
		@Autowired
		private RoomService roomService;
		
		@GetMapping
		public ResponseEntity<ApiResponse<List<RoomDto>>> findAllRooms(){
			List<RoomDto> roomDtos = roomService.findAllRooms();
			String message = roomDtos.isEmpty() ? "查詢失敗" : "查詢成功"  ;
			return ResponseEntity.ok(ApiResponse.success(message, roomDtos));
		}
		
		@GetMapping("/{roomId}")
		public ResponseEntity<ApiResponse<RoomDto>> getRoomById(@PathVariable Integer roomId){
				RoomDto roomDto = roomService.getRoomById(roomId);
				return ResponseEntity.ok(ApiResponse.success("查詢成功", roomDto));
		}
		@DeleteMapping("/{roomId}")
		public ResponseEntity<ApiResponse<Integer>> deleteRoomById(@PathVariable Integer roomId){
				roomService.deleteRoom(roomId);
				return ResponseEntity.ok(ApiResponse.success("刪除" + roomId + "號房成功", roomId));
		}
		@PostMapping
		public ResponseEntity<ApiResponse<RoomDto>> addRoom(@RequestBody RoomDto roomDto){

				roomService.addRoom(roomDto);
				return ResponseEntity.ok(ApiResponse.success("新增成功", roomDto));
		}
		@PutMapping("/{roomId}")
		public ResponseEntity<ApiResponse<RoomDto>> updateRoom(@PathVariable Integer roomId, @RequestBody RoomDto roomDto){
				roomService.updateRoom(roomId, roomDto);
				return ResponseEntity.ok(ApiResponse.success("修改成功",roomDto));
		}
		@ExceptionHandler({RoomException.class})
		public ResponseEntity<ApiResponse<Void>> handleRoomExceptions(RoomException e){
			return ResponseEntity.status(500).body(ApiResponse.error(500, e.getMessage()));
		}
}
