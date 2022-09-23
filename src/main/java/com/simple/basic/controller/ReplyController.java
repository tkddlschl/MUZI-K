package com.simple.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.simple.basic.command.ReplyDTO;
import com.simple.basic.reply.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	ReplyService replyService;
	
	@PostMapping("/replyForm")
	public String replyForm(ReplyDTO dto) {

		boolean result = replyService.replyRegist(dto);

		return "redirect:/communityList";
	}
}
