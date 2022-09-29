package com.simple.basic.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.basic.command.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper replyMapper;
	
	@Override
	public boolean replyRegist(ReplyDTO dto) {

		return replyMapper.replyRegist(dto);
	}

	@Override
	public List<ReplyDTO> replyList(int c_num) {

		return replyMapper.replyList(c_num);
	}

	@Override
	public List<ReplyDTO> replyCount() {
		// TODO Auto-generated method stub
		return replyMapper.replyCount();
	}

	@Override
	public boolean replyUpdate(ReplyDTO dto) {
		return replyMapper.replyUpdate(dto);
	}

	@Override
	public boolean replyDelete(ReplyDTO dto) {
		// TODO Auto-generated method stub
		return replyMapper.replyDelete(dto);
	}

	@Override
	public boolean userReplyDelete(String u_id) {
		return replyMapper.userReplyDelete(u_id);
	}
}
