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
import com.simple.basic.command.CategoryDTO;
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
	
	@GetMapping("/playlist")
	public String playlist(@RequestParam("u_id") String u_id, Model model) {
		List<CategoryDTO> list3 = categoryService.listAll();
		List<PlayDTO> play = playService.playlist(u_id);
		List<RecodeDTO> playlist1 = recodeService.recodeplay1(u_id);
		List<UploadDTO> playlist2 = recodeService.recodeplay2(u_id);

		model.addAttribute("list3", list3);
		model.addAttribute("play", play);
		model.addAttribute("playlist1", playlist1);
		model.addAttribute("playlist2", playlist2);

		return "/playlist";
	}

	@PostMapping("/nextSong")
	@ResponseBody
	public PlayDTO nextSong(@RequestBody PlayDTO playDTO) {
		PlayDTO next = playService.nextSong(playDTO.getU_id(), playDTO.getR_num());
		return next;
	}
}
