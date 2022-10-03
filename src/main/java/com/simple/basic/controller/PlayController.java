package com.simple.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.basic.category.CategoryService;
import com.simple.basic.command.PlayDTO;
import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;
import com.simple.basic.play.PlayService;
import com.simple.basic.recode.RecodeService;

@Controller
public class PlayController {

	@Autowired
	PlayService playService;
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	RecodeService recodeService;
	
	@PostMapping("/addlist")
	public String addlist(PlayDTO dto) {
		
		boolean result = playService.addlist(dto);
		
		return "redirect:/main";
	
	}
	
	
}
