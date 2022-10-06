package com.simple.basic.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

	private String u_id;
	private String u_pw;
	private String u_email;
	private String u_nick;
	private char u_type;
	
	private String follower; // 내가 팔로우한 사람e
	private String u_image; // 유저 프로필 사진
	private String u_path; // 유저 프로필 사진 저장 주소
	private int followerCount; // 팔로워 숫자
	private int isFollow; //로그인 한 사람 다른 아티스트 팔로우 여부
}
