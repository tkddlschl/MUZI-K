package com.simple.basic.notice;

import java.util.List;

import com.simple.basic.command.NoticeDTO;
import com.simple.basic.util.Criteria2;

public interface NoticeService {
	public boolean noticeRegist(NoticeDTO dto); //등록	
	public List<NoticeDTO> getList(Criteria2 cri); //페이징 조회
	public NoticeDTO getDetail(int n_num); //상세조회
	public int getTotal(Criteria2 cri); //전체게시글수
	public boolean noticeUpdate(NoticeDTO dto); //수정
	public boolean noticeDelete(int n_num); //삭제
}
