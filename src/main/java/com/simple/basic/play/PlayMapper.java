package com.simple.basic.play;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.PlayDTO;

@Mapper
public interface PlayMapper {
	List<PlayDTO> playlist(String u_id);

	public int listCheck(PlayDTO dto);

	public boolean addList(PlayDTO dto);

	public boolean rmList(PlayDTO dto);

}
