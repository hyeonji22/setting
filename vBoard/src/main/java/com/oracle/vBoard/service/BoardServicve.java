package com.oracle.vBoard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.vBoard.dto.Board;
import com.oracle.vBoard.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServicve  {
	
	private final BoardMapper boardmapper;
	
	public List<Board> getboardList(Board board) {
		return boardmapper.getList();
	}

}
