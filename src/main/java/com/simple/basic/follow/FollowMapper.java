package com.simple.basic.follow;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.FollowDTO;

@Mapper
public interface FollowMapper {
	public void follow(FollowDTO follow); // 팔로우 하기
	public void unfollow(FollowDTO follow); // 언팔 하기
	public int isFollow(FollowDTO follow); // 맞팔 확인
	public List<FollowDTO> selectActiveUserList(String u_id); // 팔로잉 리스트 조회
	public List<FollowDTO> selectPassiveUserList(String f_passiveUser); // 팔로워 리스트 조회
	public void deleteUserFollow(String u_id); // 회원 탈퇴시 팔로우 삭제
}
