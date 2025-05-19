package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Exception.RoomException;
import com.example.demo.model.dto.RoomDto;
import com.example.demo.service.RoomService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value ={"/room","/rooms"})
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping
	public String getRooms(Model model) {
		RoomDto roomDto = new RoomDto(); 
		List<RoomDto> roomDtos = roomService.findAllRooms();
		model.addAttribute("roomDto", roomDto);
		model.addAttribute("roomDtos", roomDtos);
		return "room/room";
	}
	// Spring 提供了另一種寫法
	/*@GetMapping
	public String getRooms(Model model, @ModelAttribute RoomDto roomDto) {
		List<RoomDto> roomDtos = roomService.findAllRooms();
		model.addAttribute("roomDtos", roomDtos);
		return "room/room";
	}*/
	@GetMapping("/{roomId}")
	public String getRoom(@PathVariable Integer roomId, Model model) {
		RoomDto roomDto = roomService.getRoomById(roomId);
		model.addAttribute("roomDto",roomDto);
		return "room/room_update";
		
	}
	// controller 加上 @Valid 以及 BindingResult
	@PostMapping
	public String addRooms(@Valid RoomDto roomdto, BindingResult bindingResult, Model model) {
	    if (bindingResult.hasErrors()) {
	        // 有錯誤就返回原頁面，並保留錯誤訊息與使用者輸入資料
	        model.addAttribute("roomDtos", roomService.findAllRooms()); // 保留列表
	        return "room/room";
	    }
	    roomService.addRoom(roomdto);
	    return "redirect:/rooms";
	}
	
	@GetMapping("/delete/{roomId}")
	public String deleteRooms(@PathVariable("roomId") Integer roomId) {
		roomService.deleteRoom(roomId);
		return "redirect:/rooms";
	}
	
	@PutMapping("/update/{roomId}")
	public String updateRoom(@PathVariable Integer roomId ,@Valid RoomDto roomdto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
	        // 有錯誤就返回原頁面，並保留錯誤訊息與使用者輸入資料
	        return "room/room_update";
	    }
		roomService.updateRoom(roomId, roomdto);
		return "redirect:/rooms";
	}
	@ExceptionHandler({RoomException.class})
	public String handleException(Exception e, Model model) {
		e.printStackTrace();
		model.addAttribute("message", e.getMessage());
		return "error";
	}
}
