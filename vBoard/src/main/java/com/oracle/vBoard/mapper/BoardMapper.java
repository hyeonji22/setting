package com.oracle.vBoard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.oracle.vBoard.dto.Board;

@Mapper
public interface BoardMapper {

	List<Board> getBoardList(Map<String, Object> dataMap);

	Board getboardDetail(Long id);

	int insertBoard(Board board);

	int updateBoard(Board board);

	int deleteBoard(Long id);

	int totallist(Board board);

	



}
