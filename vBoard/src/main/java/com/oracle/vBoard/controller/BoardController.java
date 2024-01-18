package com.oracle.vBoard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.vBoard.dto.Board;
import com.oracle.vBoard.service.BoardServicve;

import lombok.RequiredArgsConstructor;




@RestController
@RequiredArgsConstructor
public class BoardController {
	

	private final BoardServicve boardservice;
	
	@GetMapping("/")
	public List<Board> BoardList(Board board) {

		List<Board> boardList = boardservice.getboardList(board);
		
		return boardList;
	}
}
