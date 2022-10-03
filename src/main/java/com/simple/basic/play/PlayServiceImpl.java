package com.simple.basic.play;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.basic.command.PlayDTO;

@Service
public class PlayServiceImpl implements PlayService {

	@Autowired
	PlayMapper playMapper;

	@Override
	public boolean addlist(PlayDTO dto) {
		return playMapper.addlist(dto);
	}

	@Override
	public List<PlayDTO> playlist(String u_id) {
		return playMapper.playlist(u_id);
	}
}
