package com.simple.basic.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.CommunityDTO;
import com.simple.basic.util.Criteria;

@Mapper
public interface CommunityMapper {
	public boolean communityRegist(CommunityDTO dto); //등록
	
	public List<CommunityDTO> getList(Criteria cri); //페이징 조회
	public List<CommunityDTO> getMyPost(Criteria cri);
	
	public int getTotal(Criteria cri); //전체게시글수
	public int getMyTotal(Criteria cri); //전체게시글수
	
	public CommunityDTO getDetail(int c_num); //상세조회
	
	public boolean communityUpdate(CommunityDTO dto); //수정
	public boolean communityDelete(int c_num); //삭제
	public boolean userCommunityDelete(String u_id); // 유저가 탈퇴할때 작성한 게시글 삭제
}
