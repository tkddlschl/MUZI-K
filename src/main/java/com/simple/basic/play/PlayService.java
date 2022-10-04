package com.simple.basic.play;

import java.util.List;

import com.simple.basic.command.PlayDTO;

public interface PlayService {

	List<PlayDTO> playlist(String u_id);

	public int listCheck(PlayDTO dto);

	public boolean addList(PlayDTO dto);

	public boolean rmList(PlayDTO dto);
}
