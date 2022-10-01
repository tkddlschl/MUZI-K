package com.simple.basic.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.basic.category.CategoryService;
import com.simple.basic.command.CategoryDTO;
import com.simple.basic.command.FollowDTO;
import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;
import com.simple.basic.command.UserDTO;
import com.simple.basic.command.UserTotalDTO;
import com.simple.basic.follow.FollowService;
import com.simple.basic.recode.RecodeService;
import com.simple.basic.user.UserService;

@Controller
public class MainController {
	
	@Autowired
	RecodeService recodeService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FollowService followService;
	
	@GetMapping("/main")
	public String main(Model model, HttpSession session) {
		
		UserTotalDTO user = (UserTotalDTO)session.getAttribute("user");
		if(user != null) {
			String u_id = user.getU_id();
			List<UserDTO> art= userService.f_loginArtistList(u_id);
			model.addAttribute("art", art);
		}
		else {
			List<UserDTO> art = userService.artistList();
			model.addAttribute("art", art);
		}

		List<RecodeDTO> list1 = recodeService.recodeList();
		List<CategoryDTO> list3 = categoryService.listAll();
		
		model.addAttribute("list1", list1);
		model.addAttribute("list3", list3);
		return "index";
	}
	
	
	
	@GetMapping("/mainRecode")
	public String main2(@RequestParam(value = "sort", required = false) String sort, Model model, HttpSession session) {
		
		UserTotalDTO user = (UserTotalDTO)session.getAttribute("user");
		if(user != null) {
			String u_id = user.getU_id();
			List<UserDTO> art= userService.loginArtistList(u_id);
			model.addAttribute("art", art);
		}
		else {
			List<UserDTO> art = userService.artistList();
			model.addAttribute("art", art);
		}

		if(sort.equals("like")) {
			List<RecodeDTO> list1 = recodeService.sortLike();
			model.addAttribute("list1", list1);
		}
		else if(sort.equals("title")) {
			List<RecodeDTO> list1 = recodeService.sortName();
			model.addAttribute("list1", list1);
		}
		
		List<CategoryDTO> list3 = categoryService.listAll();
		

		model.addAttribute("list3", list3);
		return "index";
	}
	
	@PostMapping("/followSwitch")
	@ResponseBody
	public int followSwitch(@RequestBody FollowDTO followDto) {
		
		int isFollowCheck = followService.isFollow(followDto);
		
		if (isFollowCheck == 0) {
			followService.follow(followDto);
		} else {
			followService.unfollow(followDto);
		}
		return isFollowCheck;
	}
	
	
	@PostMapping("/followCount")
	@ResponseBody
	public int followCount(@RequestBody FollowDTO followDto) {
		
		int followCount = followService.followerCount(followDto.getU_id());

		return followCount;
	}

}
