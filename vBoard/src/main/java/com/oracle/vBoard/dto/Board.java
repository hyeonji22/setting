package com.oracle.vBoard.dto;

import lombok.Data;

@Data
public class Board {
	private int id;
	private String title;
	private String write;
	private String content;
	private Data regdate;
	private Data update;
	
	

}
