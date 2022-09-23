package com.simple.basic.notice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.basic.command.NoticeDTO;
import com.simple.basic.util.Criteria2;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeMapper noticeMapper;

	@Transactional(rollbackOn = Exception.class)
	@Override
	public boolean noticeRegist(NoticeDTO dto) {
		return noticeMapper.noticeRegist(dto);
	}

	@Override
	public List<NoticeDTO> getList(Criteria2 cri) {
		return noticeMapper.getList(cri);
	}

	@Override
	public NoticeDTO getDetail(int n_num) {
		return noticeMapper.getDetail(n_num);
	}

	@Override
	public boolean noticeUpdate(NoticeDTO dto) {
		return noticeMapper.noticeUpdate(dto);
	}

	@Override
	public boolean noticeDelete(int n_num) {
		return noticeMapper.noticeDelete(n_num);
	}

	@Override
	public int getTotal(Criteria2 cri) {
		return noticeMapper.getTotal(cri);
	}
}
