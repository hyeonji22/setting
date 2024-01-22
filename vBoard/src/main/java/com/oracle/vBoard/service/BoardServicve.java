package com.oracle.vBoard.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.oracle.vBoard.dto.Board;
import com.oracle.vBoard.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServicve  {
	
	private final BoardMapper boardmapper;
	//�����ȸ
	public List<Board> getBoardList(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return boardmapper.getBoardList(dataMap);
	}
	//����ȸ
	public Board getboardDetail(Long id) {
		return boardmapper.getboardDetail(id);
	}
	//�� ���
	public int register(Board board) {
		return boardmapper.insertBoard(board);
	}
	//�� ����
	public int updateBoard(Board board) {
		return boardmapper.updateBoard(board);
	}
	//�� ����
	public int deleteBoard(Long id) {
		return boardmapper.deleteBoard(id);
	}
	public int totallist(Board board) {
		// TODO Auto-generated method stub
		return boardmapper.totallist(board);
	}


}
