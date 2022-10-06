package com.simple.basic.play;

import java.util.List;

import com.simple.basic.command.PlayDTO;

public interface PlayService {

	List<PlayDTO> playlist(String u_id);

	public int listCheck(PlayDTO dto);

	public boolean addList(PlayDTO dto);

	public boolean rmList(PlayDTO dto);
	
	public PlayDTO nextSong(String u_id, int r_num);
}
