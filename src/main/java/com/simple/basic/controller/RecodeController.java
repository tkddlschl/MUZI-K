package com.simple.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simple.basic.category.CategoryService;
import com.simple.basic.command.CategoryDTO;
import com.simple.basic.command.JoinDTO;
import com.simple.basic.command.LikeDTO;
import com.simple.basic.command.PlayDTO;
import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;
import com.simple.basic.follow.FollowService;
import com.simple.basic.play.PlayService;
import com.simple.basic.recode.RecodeService;

@Controller
public class RecodeController {

	@Autowired
	RecodeService recodeService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	FollowService followService;
	
	@Autowired
	PlayService playService;

	@GetMapping("/recodeInsert") // 업로드 화면
	public String recodeInsert(Model model) {

		List<CategoryDTO> list3 = categoryService.listAll();
		model.addAttribute("list3", list3);
		return "/upload";
	}

	@GetMapping("/recodeList")
	public @ResponseBody String recodeList(@RequestParam("cat_name") String cat_name, Model model) throws Exception {

		List<JoinDTO> nickName = recodeService.nickName();
		List<RecodeDTO> list1 = recodeService.recodeList();
		List<CategoryDTO> list3 = categoryService.listAll();
		
		model.addAttribute("list1", list1);
		model.addAttribute("list3", list3);
		model.addAttribute("cat_name", cat_name);
		model.addAttribute("nickName", nickName);
		return "recodelist";
	}
	
	@GetMapping("/recode_List")
	public String recodeList2(@RequestParam(value = "sort", required = false) String sort, @RequestParam("cat_name") String cat_name, Model model) {

		List<JoinDTO> nickName = recodeService.nickName();
		
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
		model.addAttribute("cat_name", cat_name);
		model.addAttribute("nickName", nickName);
		return "/recodelist";
	}

	@GetMapping("/recodeDetail")
	public String recodeDetail(@RequestParam("r_num") int r_num, @RequestParam("u_id") String u_id, Model model) throws Exception {
		List<JoinDTO> nickName = recodeService.nickName();
		RecodeDTO dto1 = recodeService.recodeDetail1(r_num);
		UploadDTO dto2 = recodeService.recodeDetail2(r_num);
		List<CategoryDTO> list3 = categoryService.listAll();
		int ilike = recodeService.ilike(r_num);
		int follower = followService.followerCount(dto1.getU_id());
		int isCheck = recodeService.checkLike(LikeDTO.builder().r_num(r_num).u_id(u_id).build());
		
		List<PlayDTO> play = playService.playlist(u_id);
		List<RecodeDTO> playlist1 = recodeService.recodeplay1(u_id);
		List<UploadDTO> playlist2 = recodeService.recodeplay2();
		int playCheck = playService.listCheck(PlayDTO.builder().r_num(r_num).u_id(u_id).build());

		model.addAttribute("dto1", dto1);
		model.addAttribute("dto2", dto2);
		model.addAttribute("list3", list3);
		model.addAttribute("nickName", nickName);
		model.addAttribute("ilike", ilike);
		model.addAttribute("follower", follower);
		model.addAttribute("isCheck", isCheck);
		model.addAttribute("play", play);
		model.addAttribute("playlist1", playlist1);
		model.addAttribute("playlist2", playlist2);
		model.addAttribute("playCheck", playCheck);
		return "/recodeDetail";
	}
	
	@PostMapping("/recodeDelete")
	public String recodeDelete(@RequestParam("r_num") int r_num, @RequestParam("u_id") String u_id, RedirectAttributes ra) {
		boolean b = recodeService.recodeDelete(r_num);
		recodeService.deleteLike(r_num);
		if(b) {
			ra.addFlashAttribute("msg", "음원 삭제가 완료되었습니다.");
		}else {
			ra.addFlashAttribute("msg", "오류로 인해 음원 삭제가 실패되었습니다.");
		}
		ra.addAttribute("u_id", u_id);
		return "redirect:/mypage";
	}
	
	@GetMapping("/recodeModify")
	public String recodeModify(@RequestParam("r_num") int r_num, Model model) {
		RecodeDTO recode = recodeService.recodeDetail1(r_num);
		UploadDTO upload = recodeService.recodeDetail2(r_num);
		List<CategoryDTO> list3 = categoryService.listAll();
		
		
		model.addAttribute("recode", recode);
		model.addAttribute("upload", upload);
		model.addAttribute("list3", list3);
		return "recodeModify";
	}

	@PostMapping("/recodeUpdate")
	public String recodeUpdate(RecodeDTO dto, Model model,
							   @RequestParam("r_file1") MultipartFile file,
							   @RequestParam("r_image1") MultipartFile image,
							   RedirectAttributes ra) {
		boolean result = recodeService.recodeUpdate(image, file, dto);
		ra.addAttribute("u_id", dto.getU_id());
		return "redirect:/mypage";
	}

	@PostMapping("/recodeForm")
	public String recodeForm(@Valid RecodeDTO dto, Errors errors, Model model,
							 @RequestParam("r_file1") MultipartFile file,
							 @RequestParam("r_image1") MultipartFile image){
	    
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			for(FieldError err : list) {
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "형식 일치");
				} else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			model.addAttribute("dto", dto);
			return "/recodeInsert";
		}
			if(image.getContentType().contains("image") == false) { //이미지파일에 이미지가 아니면
			
			model.addAttribute("dto", dto);
			model.addAttribute("valid_image", "이미지형식만 등록가능합니다");
			return "/recodeInsert";
		}
			
			
		boolean result = recodeService.recodeInsert(image, file, dto); // 정보, 음원, 음원이미지
		return "redirect:/main";
	}
	
	@PostMapping("/likeCount")
	@ResponseBody
	public int likeCount(@RequestBody LikeDTO likeDto) {
		
		int ilike = recodeService.ilike(likeDto.getR_num());
		
		return ilike;
	}
	
	@PostMapping("/likeSwitch")
	@ResponseBody
	public int likeSwitch(@RequestBody LikeDTO likeDto) {
		
		int isCheck = recodeService.checkLike(likeDto);
		
		if (isCheck == 0) {
			recodeService.like(likeDto);
		} else {
			recodeService.unlike(likeDto);
		}
		return isCheck;
	}

}

