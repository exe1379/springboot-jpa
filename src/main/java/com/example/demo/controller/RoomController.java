package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.RoomDto;
import com.example.demo.service.RoomService;

@Controller
@RequestMapping(value ={"/room" ,"/rooms"})
public class RoomController {
	@Autowired
	private RoomService roomService;

	@GetMapping
	public String getRooms(Model model) {
		RoomDto roomDto = new RoomDto();
		List<RoomDto> roomDtos = roomService.findAllRooms();
		model.addAttribute("roomDto", roomDto);
		model.addAttribute("roomDtos", roomDtos);
		return "room/room.jsp";
	}
	
}
