package com.oracle.vBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.oracle.vBoard.dto.Board;

@Mapper
public interface BoardMapper {

	List<Board> getList();

}
