package com.simple.basic.command;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowDTO {
	private int f_num; //팔로우 번호
	private String u_id; // 팔로우 건 유저
	private String f_passiveUser; //팔로우 당한 유저
	
	private String f_activeUserId; // 팔로우 건 유저의 아이디
	private String f_passiveUserId; // 팔로우 당한 유저의 아이디
	
	private String u_image; // 유저 프로필 사진
	
	private String f_activeUserNick; // 팔로우 건 유저의 닉네임
	private String f_passiveUserNick; // 팔로우 당한 유저의 닉네임
}
