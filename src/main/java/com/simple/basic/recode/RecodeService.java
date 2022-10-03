package com.simple.basic.recode;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.simple.basic.command.JoinDTO;
import com.simple.basic.command.LikeDTO;
import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;

public interface RecodeService {
// 인서트 리스트 디테일 업데이트 딜리트
	
	public boolean recodeInsert(MultipartFile image, MultipartFile file ,RecodeDTO dto );
	public List<RecodeDTO> recodeList1();
	public List<UploadDTO> recodeList2();
	public RecodeDTO recodeDetail1(int r_num);
	public UploadDTO recodeDetail2(int r_num);
	public boolean recodeDelete(int r_num);
	public boolean recodeUpdate(MultipartFile image, MultipartFile file, RecodeDTO dto);
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
	public List<UploadDTO> recodeplay2(int r_num);
}
