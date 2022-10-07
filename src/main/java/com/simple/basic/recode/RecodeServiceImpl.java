package com.simple.basic.recode;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.simple.basic.command.JoinDTO;
import com.simple.basic.command.LikeDTO;
import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;

@Service
public class RecodeServiceImpl implements RecodeService {

	@Autowired
	RecodeMapper recodeMapper;
	@Value("${project.upload.path}")
	public String uploadPath;
	@Value("${music.upload.path}")
	public String musicPath;
	
	
	// 한번에 업로드 될 수 있게 transactional 사용
	@Transactional(rollbackOn = Exception.class)
	@Override
	public boolean recodeInsert(MultipartFile image, MultipartFile file, RecodeDTO dto) {
		// 레코드 데이터 업로드
		boolean result = recodeMapper.recodeInsert(dto);
		
		// 파일 업로드
		
		String origin = file.getOriginalFilename();
	    String filename = origin.substring(origin.lastIndexOf("\\") + 1);
	    String uuid = UUID.randomUUID().toString();
	    if(filename == null || filename == "") {
	    	uuid = null;
	    }
	    String r_fileName = uuid + "_" + filename;
	    String save = musicPath + r_fileName;
	    if(filename == null || filename == "") {
	    	save = uploadPath + filename;
	    }
	    
	    
	    String imageOrigin = image.getOriginalFilename();
	    String imageName = imageOrigin.substring(origin.lastIndexOf("/") + 1);
	    String uuid2 = UUID.randomUUID().toString();
	    if(imageName == null || imageName == "") {
	    	uuid2 = null;
	    }
	    
	    String r_imageName = uuid2 + "_" + imageName;
	    String imageSave = uploadPath + uuid2 + "_" + imageName;
	    if(imageName == null || imageName == "") {
	    	imageSave = uploadPath + imageName;
	    }
	    
	    try {
	    	File saveFile = new File(save);
	    	File saveImage= new File(imageSave);
	    	
	    	file.transferTo(saveFile);
	    	image.transferTo(saveImage);
	    	
	    } catch (Exception e) {
	    	
	    	System.out.println("upload error 입니다.");
	    }
	    
	    //selectkey 방식으로 r_num 가져오기 -> mapper

	    //파일 경로 db 업로드
	    recodeMapper.recodeFileInsert(
	    		UploadDTO.builder().r_file(r_fileName).r_image(r_imageName).r_path(uploadPath).r_name(dto.getR_name()).build()
	    		);
	    
	    
		
		return result;
	}

	@Override
	public List<RecodeDTO> recodeList() {
		return recodeMapper.recodeList();
	}

	@Override
	public List<RecodeDTO> sortLike() {
		return recodeMapper.sortLike();
	}

	@Override
	public List<RecodeDTO> sortName() {
		return recodeMapper.sortName();
	}
	
	@Override
	public RecodeDTO recodeDetail1(int r_num) {
		return recodeMapper.recodeDetail1(r_num);
	}

	@Override
	public UploadDTO recodeDetail2(int r_num) {
		return recodeMapper.recodeDetail2(r_num);
	}
	
	@Override
	public boolean recodeDelete(int r_num) {
		return recodeMapper.recodeDelete(r_num);
	}
	
	@Override
	public boolean recodeUpdate(MultipartFile image, MultipartFile file, RecodeDTO dto) {
		boolean result = recodeMapper.recodeUpdate(dto);
		
		String origin = file.getOriginalFilename();
	    String filename = origin.substring(origin.lastIndexOf("\\") + 1);
	    String uuid = UUID.randomUUID().toString();
	    if(filename == null || filename == "") {
	    	uuid = null;
	    }
	    String r_fileName = uuid + "_" + filename;
	    String save = musicPath + r_fileName;
	    if(filename == null || filename == "") {
	    	save = uploadPath + filename;
	    }
	    
	    
	    String imageOrigin = image.getOriginalFilename();
	    String imageName = imageOrigin.substring(origin.lastIndexOf("\\") + 1);
	    String uuid2 = UUID.randomUUID().toString();
	    if(imageName == null || imageName == "") {
	    	uuid2 = null;
	    }
	    
	    String r_imageName = uuid2 + "_" + imageName;
	    String imageSave = uploadPath + uuid2 + "_" + imageName;
	    if(imageName == null || imageName == "") {
	    	imageSave = uploadPath + imageName;
	    }
	    
	    try {
	    	File saveFile = new File(save);
	    	File saveImage= new File(imageSave);
	    	
	    	file.transferTo(saveFile);
	    	image.transferTo(saveImage);
	    	
	    } catch (Exception e) {
	    	System.out.println("upload error 입니다.");
	    }
		
		recodeMapper.recodeImgUpdate(
				UploadDTO.builder().r_file(r_fileName).r_image(r_imageName).r_path(uploadPath).r_name(dto.getR_name()).r_num(dto.getR_num()).build()
				);
		
		return result;
	}

	@Override
	public List<JoinDTO> nickName() {
		return recodeMapper.nickName();
	}
	
	@Override
	public boolean like(LikeDTO dto) {
		// TODO Auto-generated method stub
		return recodeMapper.like(dto);
	}

	@Override
	public int ilike(int r_num) {
		return recodeMapper.ilike(r_num);
	}

	@Override
	public int checkLike(LikeDTO dto) {
		return recodeMapper.checkLike(dto);
	}

	@Override
	public boolean unlike(LikeDTO dto) {
		return recodeMapper.unlike(dto);
	}

	@Override
	public boolean deleteLike(int r_num) {
		return recodeMapper.deleteLike(r_num);
	}

	@Override
	public boolean userRecodeDelete(String u_id) {
		return recodeMapper.userRecodeDelete(u_id);
	}

	@Override
	public boolean userLikeDelete(String u_id) {
		return recodeMapper.userLikeDelete(u_id);
	}

	@Override
	public boolean userGiveLikeDelete(String u_id) {
		return recodeMapper.userGiveLikeDelete(u_id);
	}
	
	@Override
	public List<RecodeDTO> recodeplay1(String u_id) {
		return recodeMapper.recodeplay1(u_id);
	}

	@Override
	public List<UploadDTO> recodeplay2() {
		return recodeMapper.recodeplay2();
	}

}
