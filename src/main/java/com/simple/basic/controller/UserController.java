package com.simple.basic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simple.basic.category.CategoryService;
import com.simple.basic.command.CategoryDTO;
import com.simple.basic.command.FollowDTO;
import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;
import com.simple.basic.command.UserDTO;
import com.simple.basic.command.UserTotalDTO;
import com.simple.basic.command.UserUploadDTO;
import com.simple.basic.email.EmailService;
import com.simple.basic.follow.FollowService;
import com.simple.basic.user.UserService;


@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	FollowService followService;
	
	@Autowired
	private EmailService emailService;
	
	
	@GetMapping("/login")
	public String userLogin() {
		
		return "login";
	}
	
	@GetMapping("/signUp")
	public String userInsert() {
		
		return "register";
	}
	
	@GetMapping("/logout")
	public String userLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}
	
	@GetMapping("/mypage")
	public String userMypage() {
		
		return "mypage";
	}
	
	@GetMapping("/userInfo")
	public String userInfo() {
		
		return "userInfo";
	}
	
	@GetMapping("/artistList")
	public String artistList(Model model) {
		List<UserDTO> art= userService.artistList();
		List<CategoryDTO> list3 = categoryService.listAll();
		
		
		model.addAttribute("art", art);
		model.addAttribute("list3", list3);
		return "artistList";
	}
	
	@PostMapping("/artistListForm")
	public String artistFollowForm(@RequestParam("u_id")String u_id, @RequestParam("f_passiveUser")String f_passiveUser) {
		int isFollow = followService.isFollow(FollowDTO.builder().u_id(u_id).f_passiveUser(f_passiveUser).build());
		if(isFollow == 0) {
			followService.follow(FollowDTO.builder().u_id(u_id).f_passiveUser(f_passiveUser).build());
		}
		else {
			followService.unfollow(FollowDTO.builder().u_id(u_id).f_passiveUser(f_passiveUser).build());
		}
		return "redirect:/artistList";
	}
	
	
	@GetMapping("/artistDetail")
	public String artistDetail(@RequestParam("u_id")String u_id, @RequestParam("u_nick")String u_nick, Model model) {
		List<CategoryDTO> list3 = categoryService.listAll();
		List<RecodeDTO> list1 = userService.myRecode1(u_id);
	    List<UploadDTO> list2 = userService.myRecode2();
	    UserUploadDTO profile = userService.artistImgDetail(u_id);
	    List<FollowDTO> u_list = followService.selectActiveUserList(u_id);
	    List<FollowDTO> f_list = followService.selectActiveUserList(u_id);
	    int follower = followService.followerCount(u_id);
	    int following = followService.followingCount(u_id);

	    model.addAttribute("list1", list1);
	    model.addAttribute("list2", list2);
	    model.addAttribute("u_id", u_id);
	    model.addAttribute("u_nick", u_nick);
	    model.addAttribute("profile", profile);
		model.addAttribute("list3", list3);
		model.addAttribute("u_list", u_list);
		model.addAttribute("f_list", f_list);
		model.addAttribute("follower", follower);
		model.addAttribute("following", following);
		return "artistDetail";
	}
	
	
	@PostMapping("/login")
	public String loginForm(UserTotalDTO dto, HttpServletRequest request, RedirectAttributes rttr, Model model) {
		HttpSession session = request.getSession();
		UserTotalDTO user = userService.login(dto);
		
		if(user == null) {
			session.setAttribute("user", null);
			model.addAttribute("msg", "아이디, 비밀번호가 일치하지 않습니다.");
			return "login";
		}
		else {
			session.setAttribute("user", user);
		}
		
		//VO를 가지고 로그인 SQL 실행
		//로그인 성공이라 가정
				
		return "redirect:/main";
	}
	
	@PostMapping("/mypageForm")
	public String mypageForm(@RequestParam("u_id1") String u_id, Model model) {
		List<RecodeDTO> list1 = userService.myRecode1(u_id);
		List<UploadDTO> list2 = userService.myRecode2();
		List<CategoryDTO> list3 = categoryService.listAll();
		int follower = followService.followerCount(u_id);
		int following = followService.followingCount(u_id);
		
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("follower", follower);
		model.addAttribute("following", following);
		return "/mypage";
	}
	
	@PostMapping("/userUpdate")
	public String userUpdate(HttpServletRequest request, UserTotalDTO dto) {
		HttpSession session = request.getSession();
		boolean b = userService.userUpdate(dto);
		UserTotalDTO user = dto;
		session.setAttribute("user", user);
		
		return "redirect:/main";
	}
	
	@PostMapping("/userDelete")
	public String userDelete(@RequestParam("u_id") String u_id, RedirectAttributes ra, HttpSession session) {
		boolean b = userService.userDelete(u_id);
		if(b) {
			ra.addFlashAttribute("msg", "수정이 완료되었습니다.");
		}else {
			ra.addFlashAttribute("msg", "오류로 인해 수정이 실패되었습니다.");
		}
		session.invalidate();
		return "redirect:/main";
	}
	
	@PostMapping("/userForm")
	public String userForm(@Valid UserDTO dto, Errors errors, Model model, String u_id, String u_nick,
						   @RequestParam("u_image1") MultipartFile image) {
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			for (FieldError err : list) {
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "유형 선택은 필수입니다.");
				}
				else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			
			// 다시 회원가입 화면으로
			model.addAttribute("dto", dto);
			return "register";
		}
//		if(image.getContentType().contains("image") == false) {
//			model.addAttribute("dto", dto);
//			model.addAttribute("valid_image", "이미지형식만 등록가능합니다");
//			return "register";
//		}
		int idResult = userService.idCheck(u_id);
		int nickResult = userService.nickCheck(u_nick);
		
		if(idResult == 1 || nickResult == 1) {
			return "register";
		}
		boolean b = userService.userInsert(dto, image);
		return "redirect:/main";
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public int idCheck(String u_id) {
		int result = userService.idCheck(u_id);
		return result;
	}
	
	@PostMapping("/nickCheck")
	@ResponseBody
	public int nickCheck(String u_nick) {
		int result = userService.nickCheck(u_nick);
		return result;
	}
	
	@PostMapping("/sendCode")
	@ResponseBody
	public String sendCode(String u_email) throws Exception {
		String e_code = userService.createCode();
		System.out.println("인증코드 : " + e_code);
		System.out.println("email 주소 : " + u_email);
		emailService.sendEmailMessage(u_email, e_code);
		 
		return e_code;
	}
	
	@PostMapping("/checkCode")
	@ResponseBody
	public String checkCode(String e_code){
		return e_code;
	}

}
