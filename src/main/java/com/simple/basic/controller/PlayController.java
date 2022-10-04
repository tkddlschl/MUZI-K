package com.simple.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.basic.category.CategoryService;
import com.simple.basic.command.LikeDTO;
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
	
	
	
	@PostMapping("/playSwitch")
	@ResponseBody
	public int playSwitch(@RequestBody PlayDTO playDto) {
		
		int playCheck = playService.listCheck(playDto);
		
		if (playCheck == 0) {
			playService.addList(playDto);
		} else {
			playService.rmList(playDto);
		}
		return playCheck;
	}

	
	
}
