package com.simple.basic.play;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayServiceImpl implements PlayService {

	@Autowired
	PlayMapper playMapper;

	@Override
	public boolean addlist(int r_num, String u_id) {
		// TODO Auto-generated method stub
		return false;
	}
}
