package com.simple.basic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.simple.basic.community.CommunityService;
import com.simple.basic.email.EmailService;
import com.simple.basic.follow.FollowService;
import com.simple.basic.recode.RecodeService;
import com.simple.basic.reply.ReplyService;
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
	EmailService emailService;
	
	@Autowired
	RecodeService recodeService;
	
	@Autowired
	CommunityService communityService;
	
	@Autowired
	ReplyService replyService;
	
	
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
	public String userMypage(@RequestParam("u_id") String u_id, Model model) {
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
	
	@GetMapping("/userInfo")
	public String userInfo(Model model) {
		List<CategoryDTO> list3 = categoryService.listAll();
		
		model.addAttribute("list3", list3);
		return "userInfo";
	}
	
	@GetMapping("/artistList")
	public String artistList(Model model, HttpSession session) {
		UserTotalDTO user = (UserTotalDTO)session.getAttribute("user");
		if(user != null) {
			String u_id = user.getU_id();
			List<UserDTO> art= userService.loginArtistList(u_id);
			model.addAttribute("art", art);
		}
		else {
			List<UserDTO> art = userService.a_basicSort();
			model.addAttribute("art", art);
		}
		List<CategoryDTO> list3 = categoryService.listAll();
		
		
		model.addAttribute("list3", list3);
		return "artistList";
	}
	
	@GetMapping("/artist_List")
	public String artistList2(@RequestParam(value = "sort", required = false) String sort, Model model, HttpSession session) {
		UserTotalDTO user = (UserTotalDTO)session.getAttribute("user");
		if(user != null) {
			String u_id = user.getU_id();
			if(sort.equals("follower")) {
				List<UserDTO> art= userService.f_loginArtistList(u_id);
				model.addAttribute("art", art);
			}
			else if(sort.equals("name")) {
				List<UserDTO> art= userService.n_loginArtistList(u_id);
				model.addAttribute("art", art);
			}
		}
		else {
			if(sort.equals("follower")) {
				List<UserDTO> art= userService.artistList();
				model.addAttribute("art", art);
			}
			else if(sort.equals("name")) {
				List<UserDTO> art= userService.a_nameSort();
				model.addAttribute("art", art);
			}
		}
		List<CategoryDTO> list3 = categoryService.listAll();
		
		
		model.addAttribute("list3", list3);
		return "artistList";
	}
//	
//	@PostMapping("/artistListForm")
//	public String artistFollowForm(@RequestParam("u_id")String u_id, @RequestParam("f_passiveUser")String f_passiveUser) {
//		int isFollow = followService.isFollow(FollowDTO.builder().u_id(u_id).f_passiveUser(f_passiveUser).build());
//		if(isFollow == 0) {
//			followService.follow(FollowDTO.builder().u_id(u_id).f_passiveUser(f_passiveUser).build());
//		}
//		else {
//			followService.unfollow(FollowDTO.builder().u_id(u_id).f_passiveUser(f_passiveUser).build());
//		}
//		return "redirect:/artistList";
//	}
	
	
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
		int isLogin = userService.isLogin(dto);
		UserTotalDTO user = userService.login(dto);
		
		if(isLogin == 0) {
			model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			model.addAttribute("msg2", "입력하신 내용을 다시 확인해주세요.");
			return "login";
		}
		else {
			session.setAttribute("user", user);
		}
		
		return "redirect:/main";
	}
	
//	@PostMapping("/mypageForm")
//	public String mypageForm(@RequestParam("u_id1") String u_id, Model model) {
//		List<RecodeDTO> list1 = userService.myRecode1(u_id);
//		List<UploadDTO> list2 = userService.myRecode2();
//		List<CategoryDTO> list3 = categoryService.listAll();
//		int follower = followService.followerCount(u_id);
//		int following = followService.followingCount(u_id);
//		
//		model.addAttribute("list1", list1);
//		model.addAttribute("list2", list2);
//		model.addAttribute("list3", list3);
//		model.addAttribute("follower", follower);
//		model.addAttribute("following", following);
//		return "/mypage";
//	}
	
	@PostMapping("/userUpdate")
	public String userUpdate(HttpServletRequest request, UserTotalDTO dto,
							 @RequestParam("u_image1") MultipartFile u_image,
							 RedirectAttributes ra,
							 HttpSession session2) {
		session2.invalidate();
		HttpSession session = request.getSession();
		boolean b = userService.userUpdate(dto, u_image);
		UserTotalDTO user = dto;
		session.setAttribute("user", user);
		ra.addAttribute("u_id", dto.getU_id());
		return "redirect:/mypage";
	}
	
	@PostMapping("/userDelete")
	public String userDelete(@RequestParam("u_id") String u_id, RedirectAttributes ra, HttpSession session) {
//		followService.deleteUserFollow(u_id); // 유저가 팔로우 했던 사람 목록에서 삭제
//		followService.deleteP_userFollow(u_id); // 유저를 팔로우 했던 사람 목록에서 삭제
//		recodeService.userRecodeDelete(u_id); // 유저가 만든 음원 삭제
//		recodeService.userLikeDelete(u_id); // 유저가 만든 음원에 대한 좋아요 삭제
//		recodeService.userGiveLikeDelete(u_id);// 유저가 좋아요를 누른 기록 삭제
//		communityService.userCommunityDelete(u_id); // 유저가 작성한 게시글 삭제
//		replyService.userReplyDelete(u_id); // 유저가 작성한 댓글 삭제
		// cascade로 완료
		
		boolean b = userService.userDelete(u_id); // 유저 데이터 삭제
		if(b) {
			ra.addFlashAttribute("msg", "탈퇴가 완료되었습니다.");
		}else {
			ra.addFlashAttribute("msg", "오류로 인해 탈퇴가 실패되었습니다.");
		}
		session.invalidate();
		return "redirect:/main";
	}
	
	@PostMapping("/userForm")
	public String userForm(@Valid UserDTO dto, @RequestParam("u_image1") MultipartFile image) {
		boolean b = userService.userInsert(dto, image);
		return "redirect:/main";
	}
	
	
	@PostMapping("/muzik/idCheck")
	@ResponseBody
	public int idCheck(String u_id) {
		int result = userService.idCheck(u_id);
		return result;
	}
	
	@PostMapping("/muzik/nickCheck")
	@ResponseBody
	public int nickCheck(String u_nick) {
		int result = userService.nickCheck(u_nick);
		return result;
	}
	
	@PostMapping("/muzik/emailCheck")
	@ResponseBody
	public int emailCheck(String u_email) {
		int result = userService.emailCheck(u_email);
		return result;
	}
	
	
	@PostMapping("/followSwitch_a")
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
	
	
	@PostMapping("/followCount_a")
	@ResponseBody
	public int followCount(@RequestBody FollowDTO followDto) {
		
		int followCount = followService.followerCount(followDto.getU_id());

		return followCount;
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
	
	@GetMapping("/forgot-id")
	public String forgotId(){
		
		return "forgot-id";
	}
	
	@GetMapping("/forgot-password")
	public String forgotPw(){
		
		return "forgot-password";
	}
	
	@GetMapping("/sendCode1")
	@ResponseBody
	public String sendCode1(String u_email)
			throws Exception {
		String e_code = userService.createCode();
		emailService.sendEmailMessage1(u_email, e_code);
				
		userService.updatePwd(e_code, u_email);	
		System.out.println(e_code);
		return "redirect:/login";
	}
		
	@PostMapping("/emailCheck1")
	@ResponseBody
	public String emailCheck1(String u_email) {
		int result = userService.emailCheck(u_email);
		String str = null;
		if(result == 1) {
			str = userService.findId(u_email);
			return str;
		}
		return str;
	}
	
	@PostMapping("/findId")
	@ResponseBody
	public String findId(String u_email) {
		String id = userService.findId(u_email);

		return id;
	}
}
