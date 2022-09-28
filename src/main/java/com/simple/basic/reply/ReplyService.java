package com.simple.basic.reply;

import java.util.List;

import com.simple.basic.command.ReplyDTO;

public interface ReplyService {
	
	public boolean replyRegist(ReplyDTO dto);

	public List<ReplyDTO> replyList(int c_num);

	public List<ReplyDTO> replyCount();

	public boolean replyUpdate(ReplyDTO dto);
	
	
	public boolean replyDelete(ReplyDTO dto);
	
}
