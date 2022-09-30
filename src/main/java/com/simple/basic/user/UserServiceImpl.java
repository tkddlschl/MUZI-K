package com.simple.basic.user;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.simple.basic.command.EmailDTO;
import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;
import com.simple.basic.command.UserDTO;
import com.simple.basic.command.UserTotalDTO;
import com.simple.basic.command.UserUploadDTO;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	@Value("${user.upload.path}")
	public String uploadPath;

	@Override
	public boolean userInsert(UserDTO dto, MultipartFile u_image) {

		boolean result = userMapper.userInsert(dto);

		String imageOrigin = u_image.getOriginalFilename();
		String imageName = imageOrigin.substring(imageOrigin.lastIndexOf("\\") + 1);
		String imageSave = uploadPath + "\\" + imageName;

		try {
			File saveImage = new File(imageSave);
			u_image.transferTo(saveImage);

		} catch (Exception e) {
			System.out.println("upload error 입니다.");
		}

		if (imageName == null || imageName == "") {
			userMapper.userImageInsert(UserUploadDTO.builder().u_email(dto.getU_email()).build());
		} else {
			userMapper.userImageInsert(
					UserUploadDTO.builder().u_image(imageName).u_path(uploadPath).u_email(dto.getU_email()).build());
		}

		return result;

	}

	@Override
	public int idCheck(String u_id) {
		return userMapper.idCheck(u_id);
	}

	@Override
	public int nickCheck(String u_nick) {
		return userMapper.nickCheck(u_nick);
	}

	@Override
	public int emailCheck(String u_email) {
		return userMapper.emailCheck(u_email);
	}

	@Transactional
	@Override
	public UserTotalDTO login(UserTotalDTO user) {
		return userMapper.login(user);
	}

	@Override
	public List<RecodeDTO> myRecode1(String u_id) {
		return userMapper.myRecode1(u_id);
	}

	@Override
	public List<UploadDTO> myRecode2() {
		return userMapper.myRecode2();
	}

	@Transactional
	@Override
	public boolean userUpdate(UserTotalDTO dto) {
		return userMapper.userUpdate(dto);
	}

	@Transactional
	@Override
	public boolean userDelete(String u_id) {
		return userMapper.userDelete(u_id);
	}

	@Override
	public List<UserDTO> artistList() {
		return userMapper.artistList();
	}

	@Override
	public UserUploadDTO artistImgDetail(String u_id) {
		return userMapper.artistImgDetail(u_id);
	}

	@Override
	public List<UserDTO> loginArtistList(String u_id) {
		return userMapper.loginArtistList(u_id);
	}

	@Override
	public String createCode() {
		// 인증 코드 생성
		StringBuilder code = new StringBuilder();
		Random rnd = new Random();
		for (int i = 0; i < 7; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				code.append((char) (rnd.nextInt(26) + 97));
				break;
			case 1:
				code.append((char) (rnd.nextInt(26) + 65));
				break;
			case 2:
				code.append((rnd.nextInt(10)));
				break;
			}
		}
		return code.toString();
	}

	@Override
	public void updatePwd(String e_code, String u_email) {
		userMapper.updatePwd(e_code,u_email);
	}

	@Override
	public String findId(String u_email) {
		return userMapper.findId(u_email);
	}

}
