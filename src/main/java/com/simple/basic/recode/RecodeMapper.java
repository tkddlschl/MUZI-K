package com.simple.basic.recode;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.JoinDTO;
import com.simple.basic.command.LikeDTO;
import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;
@Mapper
public interface RecodeMapper {
 
	public boolean recodeInsert(RecodeDTO dto);
	public boolean recodeFileInsert(UploadDTO dto);
	public List<RecodeDTO> recodeList();
	public List<RecodeDTO> sortLike();
	public List<RecodeDTO> sortName();
	public RecodeDTO recodeDetail1(int r_num);
	public UploadDTO recodeDetail2(int r_num);
	public boolean recodeDelete(int r_num);
	public boolean recodeUpdate(RecodeDTO dto);
	public boolean recodeImgUpdate(UploadDTO dto);
	public List<JoinDTO> nickName();
	
	public int checkLike(LikeDTO dto);
	public boolean like(LikeDTO dto);
	public boolean unlike(LikeDTO dto);
	public int ilike(int r_num);
	public boolean deleteLike(int r_num);
	public boolean userRecodeDelete(String u_id);
	public boolean userLikeDelete(String u_id);
	public boolean userGiveLikeDelete(String u_id);
	
	
	public List<RecodeDTO> recodeplay1(String u_id);
	public List<UploadDTO> recodeplay2();
}
