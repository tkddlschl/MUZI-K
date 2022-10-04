package com.simple.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simple.basic.command.ReplyDTO;
import com.simple.basic.reply.ReplyService;

@Controller
public class ReplyController {

   @Autowired
   ReplyService replyService;
   
   @PostMapping("/replyForm")
   public String replyForm(@RequestParam("c_num")int c_num, ReplyDTO dto, RedirectAttributes ra) {
      
      boolean result = replyService.replyRegist(dto);
      ra.addAttribute("c_num", c_num);
      
      
      return "redirect:/communityDetail";
   }
   
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