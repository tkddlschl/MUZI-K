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
	
	private String u_image; // 유저 프로필 사진
	private String u_path; // 유저 프로필 사진 저장 주소
	private String u_nick; // 유저 닉네임
	
	private int followerCount; // 팔로워 숫자
	private int followingCount; // 팔로잉 숫자
	
	private String follower; // 내가 팔로우한 사람
}
