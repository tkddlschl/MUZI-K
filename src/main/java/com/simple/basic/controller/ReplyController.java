package com.simple.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
//	@GetMapping("/replyDetail")
//	public String replyDetail(@RequestParam("reply_num")int reply_num, Model model) {
//		
//		ReplyDTO dto = replyService.getReply(reply_num);
//		
//		model.addAttribute("dto", dto);
//		return "/replyDetail";
//	}
	
	
	
	@PostMapping("/replyUpdate") 
	@ResponseBody
	public boolean replyUpdate(@RequestBody ReplyDTO dto) {
		boolean reup = replyService.replyUpdate(dto);
		
		return reup;
	}
	 
	@PostMapping("/replyDelete") 
	@ResponseBody
	public boolean replyDelete(@RequestBody ReplyDTO dto) {
		boolean redel = replyService.replyDelete(dto);
		
		return redel;
	}
	
}
