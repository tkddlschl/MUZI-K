package com.simple.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String main(Model model) {
		List<RecodeDTO> list1 = recodeService.recodeList1();
		List<UploadDTO> list2 = recodeService.recodeList2();
		List<CategoryDTO> list3 = categoryService.listAll();
		List<UserDTO> art= userService.artistList();
		List<UserTotalDTO> a_img = userService.artistImgList();
		
		
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("art", art);
		model.addAttribute("a_img", a_img);
		return "index";
	}
	
	@PostMapping("/mainForm")
	public String mainForm(@RequestParam("u_id")String u_id, @RequestParam("f_passiveUser")String f_passiveUser, Model model) {
		int isFollow = followService.isFollow(FollowDTO.builder().u_id(u_id).f_passiveUser(f_passiveUser).build());
		if(isFollow == 0) {
			followService.follow(FollowDTO.builder().u_id(u_id).f_passiveUser(f_passiveUser).build());
		}
		else {
			followService.unfollow(FollowDTO.builder().u_id(u_id).f_passiveUser(f_passiveUser).build());
		}
		
		//model.addAttribute("isFollow", isFollow);
		return "redirect:/main?isFollow=" + isFollow;
	}
}
