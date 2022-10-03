package com.simple.basic.play;

import java.util.List;

import com.simple.basic.command.PlayDTO;

public interface PlayService {

	boolean addlist(PlayDTO dto);
	List<PlayDTO> playlist(String u_id);
}
