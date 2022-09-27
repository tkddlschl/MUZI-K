package com.simple.basic.util;

import com.simple.basic.command.CommunityDTO;
import com.simple.basic.command.UserDTO;

import lombok.Data;

@Data //getter, setter, toString
public class Criteria extends UserDTO {

	private int page; //페이지번호
	private int amount; //데이터 개수
	
	private String id;
	private String title;
	private String content;
	
	
	public Criteria() {
		this(1, 10);
	}
	public Criteria(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
	}
	
	public int getPageStart() {
		return (page - 1) * amount;
	}
		
}