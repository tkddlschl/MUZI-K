package com.simple.basic.community;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.simple.basic.command.CommunityDTO;
import com.simple.basic.util.Criteria;

@Service
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	CommunityMapper communityMapper;
	
	@Transactional(rollbackOn = Exception.class)
	@Override
	public boolean communityRegist(CommunityDTO dto) {
		return communityMapper.communityRegist(dto);
	}
	
	@Override 
	public List<CommunityDTO> getList(Criteria cri) {
		return communityMapper.getList(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		return communityMapper.getTotal(cri);
	}
	
	@Override
	public CommunityDTO getDetail(int c_num) {
		return communityMapper.getDetail(c_num);
	}

	@Override
	public boolean communityUpdate(CommunityDTO dto) {
		return communityMapper.communityUpdate(dto);
	}

	@Override
	public boolean communityDelete(int c_num) {
		return communityMapper.communityDelete(c_num);
	}

	@Override
	public List<CommunityDTO> getMyPost(Criteria cri) {		
		return communityMapper.getMyPost( cri);
	}

	@Override
	public int getMyTotal(Criteria cri) {
		return communityMapper.getMyTotal(cri);
	}
}
