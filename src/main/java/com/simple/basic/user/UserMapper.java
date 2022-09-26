package com.simple.basic.user;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;
import com.simple.basic.command.UserDTO;
import com.simple.basic.command.UserTotalDTO;
import com.simple.basic.command.UserUploadDTO;

@Mapper
public interface UserMapper {
	public boolean userInsert(UserDTO dto); //회원가입
	public boolean userImageInsert(UserUploadDTO dto); //회원 이미지 업로드
	public int idCheck(String u_id); // id 중복체크
	public int nickCheck(String u_nick); // 닉네임 중복체크
	public int emailCheck(String u_email); // 이메일 중복체크
	public UserTotalDTO login(UserTotalDTO user); // 로그인
	public List<RecodeDTO> myRecode1(String u_id); // 내 음악 가져오기
	public List<UploadDTO> myRecode2(); // 내 음악 가져오기
	public boolean userUpdate(UserTotalDTO dto); //회원 정보 수정
	public boolean userDelete(String u_id); //회원 삭제
	public List<UserDTO> artistList();// 아티스트 정보 가져오기
	public UserUploadDTO artistImgDetail(String u_id); // 아티스트 각 프로필 이미지 가져오기
	public List<UserDTO> loginArtistList(String u_id);
	public String createCode();
}
