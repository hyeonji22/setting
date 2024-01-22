package com.oracle.vBoard.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Board {
	private int id;
	private String title;
	private String writer;
	private String content;
	private Timestamp regdate;
	private Timestamp updatedate;

	//페이징
	private int page;
	private int pageSize;
	private int startPage;
	private int endPage;
	private int currentPage;
	private int pageBlock;
	//검색
	private String keyword;
	private String searchType;

	
	

}
