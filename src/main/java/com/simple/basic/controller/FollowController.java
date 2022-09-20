package com.simple.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.simple.basic.command.FollowDTO;
import com.simple.basic.follow.FollowService;

@Controller
public class FollowController {

	@Autowired
	FollowService followService;
	
	@GetMapping("/followerList")
	public String followList(FollowDTO follow) {
		followService.follow(follow);
		return "followerList";
	}
	
	@GetMapping("/followingList")
	public String followingList() {
		
		return "followingList";
	}
}
