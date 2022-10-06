package com.simple.basic.play;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.basic.command.PlayDTO;
import com.simple.basic.command.RecodeDTO;

@Service
public class PlayServiceImpl implements PlayService {

	@Autowired
	PlayMapper playMapper;

	
	@Override
	public List<PlayDTO> playlist(String u_id) {
		return playMapper.playlist(u_id);
	}

	@Override
	public int listCheck(PlayDTO dto) {
		return playMapper.listCheck(dto);
	}

	@Override
	public boolean addList(PlayDTO dto) {
		
		return playMapper.addList(dto);
	}

	@Override
	public boolean rmList(PlayDTO dto) {
		return playMapper.rmList(dto);
	}

	@Override
	public PlayDTO nextSong(String u_id,int r_num) {
		return playMapper.nextSong(u_id, r_num);
	}
}
