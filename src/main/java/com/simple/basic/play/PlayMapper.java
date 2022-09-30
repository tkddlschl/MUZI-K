package com.simple.basic.play;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayMapper {
	public boolean listAdd(int r_num, String u_id);
}
