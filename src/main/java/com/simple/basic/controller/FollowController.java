package com.simple.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simple.basic.command.FollowDTO;
import com.simple.basic.follow.FollowService;

@Controller
public class FollowController {

	@Autowired
	FollowService followService;
	
	@GetMapping("/followerList")
	public String followList(@RequestParam("u_id") String u_id, Model model) {
		List<FollowDTO> p_list = followService.selectPassiveUserList(u_id);
		model.addAttribute("p_list", p_list);
		return "followerList";
	}
	
	@GetMapping("/followingList")
	public String followingList(@RequestParam("u_id") String u_id, Model model) {
		List<FollowDTO> list = followService.selectActiveUserList(u_id);
		
		model.addAttribute("list", list);
		return "followingList";
	}
	
	@PostMapping("/followerDelete")
	public String followerDelete(@RequestParam("u_id") String u_id, @RequestParam("f_passiveUser")String f_passiveUser, RedirectAttributes ra) {
		followService.unfollow(FollowDTO.builder().u_id(u_id).f_passiveUser(f_passiveUser).build());
		ra.addAttribute("u_id", f_passiveUser);
		return "redirect:/followerList";
	}
	
	@PostMapping("/followerInsert")
	public String followerInsert(@RequestParam("u_id") String u_id, @RequestParam("f_passiveUser")String f_passiveUser, RedirectAttributes ra) {
		int isFollow = followService.isFollow(FollowDTO.builder().u_id(f_passiveUser).f_passiveUser(u_id).build());
		if(isFollow == 0) {
			followService.follow(FollowDTO.builder().u_id(f_passiveUser).f_passiveUser(u_id).build());
			ra.addFlashAttribute("isFollow", isFollow);
		}
		ra.addAttribute("u_id", f_passiveUser);
		return "redirect:/followerList";
	}
	
	@PostMapping("/followingDelete")
	public String followingDelete(@RequestParam("u_id") String u_id, @RequestParam("f_passiveUser")String f_passiveUser, RedirectAttributes ra) {
		followService.unfollow(FollowDTO.builder().u_id(u_id).f_passiveUser(f_passiveUser).build());
		ra.addAttribute("u_id", u_id);
		return "redirect:/followingList";
	}
}
