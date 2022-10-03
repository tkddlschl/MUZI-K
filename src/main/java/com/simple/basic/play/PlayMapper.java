package com.simple.basic.play;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.PlayDTO;

@Mapper
public interface PlayMapper {
	boolean addlist(PlayDTO dto);
	List<PlayDTO> playlist(String u_id);
}
