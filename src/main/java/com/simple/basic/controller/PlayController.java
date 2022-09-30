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
import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;
import com.simple.basic.play.PlayService;
import com.simple.basic.recode.RecodeService;
import com.simple.basic.user.UserService;

@Controller
public class PlayController {

	@Autowired
	PlayService playService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	RecodeService recodeService;
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/playlistAdd")
	public String playlistAdd(@RequestParam("r_num")int r_num, @RequestParam("u_id")String u_id , RedirectAttributes ra) {

		boolean result = playService.addlist(r_num, u_id);
		ra.addAttribute("r_num", r_num);
		ra.addAttribute("u_id", u_id);
		return "/recodeDetail";
		
	}
	@GetMapping("playlist")
	public String playlist(@RequestParam("u_id")String u_id, String cat_name, Model model) {
		List<RecodeDTO> list1 = userService.myRecode1(u_id);
		List<UploadDTO> list2 = userService.myRecode2();
		List<CategoryDTO> list3 = categoryService.listAll();
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		return "playlist";
	}
	
	@GetMapping("playlistDetail")
	public String playlistDetail() {
	
		return "playlistDetail";
	}
}
