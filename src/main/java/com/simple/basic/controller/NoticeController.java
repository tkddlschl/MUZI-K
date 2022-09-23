package com.simple.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simple.basic.category.CategoryService;
import com.simple.basic.command.CategoryDTO;
import com.simple.basic.command.NoticeDTO;
import com.simple.basic.notice.NoticeService;
import com.simple.basic.util.Criteria2;
import com.simple.basic.util.PageDTO2;

@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;
	
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/noticeRegist")
	public String commuInsert(Model model) {
		List<CategoryDTO> list3 = categoryService.listAll();
		
		model.addAttribute("list3", list3);
		return "noticeRegist";
	}
	
	@GetMapping("/noticeList")
	public String commuList(Model model, @ModelAttribute("cri") Criteria2 cri) {
		List<CategoryDTO> list3 = categoryService.listAll();
		List<NoticeDTO> list = noticeService.getList(cri); //데이터
		int total = noticeService.getTotal(cri); //전체게시글수
		PageDTO2 pageDTO2 = new PageDTO2(cri, total); //페이지네이션
		
		model.addAttribute("list", list);
		model.addAttribute("pageDTO", pageDTO2);
		model.addAttribute("list3", list3);
		return "noticeList";
	}
	
	@GetMapping("/noticeDetail")
	public String commuDetail(@RequestParam("n_num") int n_num, Model model) {
		List<CategoryDTO> list3 = categoryService.listAll();
		NoticeDTO dto = noticeService.getDetail(n_num);
		
		model.addAttribute("NoticeDTO", dto);
		model.addAttribute("list3", list3);
		return "noticeDetail";
	}
	
	@PostMapping("/noticeDelete")
	public String commuDelete(@RequestParam("n_num") int n_num, RedirectAttributes RA) {
		boolean result = noticeService.noticeDelete(n_num);
		if(result) {
			RA.addFlashAttribute("msg", "삭제 되었습니다");
		} else {
			RA.addFlashAttribute("msg", "삭제에 실패했습니다");
		}	
		return "redirect:/noticeList";
	}
	
	@PostMapping("/noticeUpdate")
	public String commuUpdate(NoticeDTO dto, RedirectAttributes RA) {
		boolean result = noticeService.noticeUpdate(dto);
			if(result) {
				RA.addFlashAttribute("msg", "정상 수정 되었습니다");
			} else {
				RA.addFlashAttribute("msg", "정보 수정에 실패했습니다");
			}
		return "redirect:/noticeList";
	}
	
	@PostMapping("/noticeForm")
	public String commuForm(NoticeDTO dto,Model model) {
		boolean result = noticeService.noticeRegist(dto);
		return "redirect:/noticeList";
	}
	
}
