package com.simple.basic.util;

import com.simple.basic.command.CommunityDTO;

import lombok.Data;

@Data //getter, setter, toString
public class Criteria extends CommunityDTO {

	private int page; //페이지번호
	private int amount; //데이터 개수
	private String keyword; // 검색 키워드
	
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