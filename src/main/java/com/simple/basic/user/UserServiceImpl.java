package com.simple.basic.user;


import java.io.File;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	    String imageSave = uploadPath + "\\"  + imageName;
	    
	    try {
	    	File saveImage= new File(imageSave);
	    	
	    	u_image.transferTo(saveImage);
	    	
	    } catch (Exception e) {
	    	System.out.println("upload error 입니다.");
	    }
	    
	    userMapper.userImageInsert(UserUploadDTO.builder().u_image(imageName).u_path(uploadPath).u_email(dto.getU_email()).build());
	    
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
	public List<UserTotalDTO> artistImgList() {
		return userMapper.artistImgList();
	}

	@Override
	public UserUploadDTO artistImgDetail(String u_id) {
		return userMapper.artistImgDetail(u_id);
	}
}
