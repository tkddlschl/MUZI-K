package com.simple.basic.follow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.basic.command.FollowDTO;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowMapper followMapper;

	@Override
	public void follow(FollowDTO follow) {
		followMapper.follow(follow);
	}

	@Override
	public void unfollow(FollowDTO follow) {
		followMapper.unfollow(follow);
	}

	@Override
	public int isFollow(FollowDTO follow) {
		return followMapper.isFollow(follow);
	}

	@Override
	public List<FollowDTO> selectActiveUserList(String u_id) {
		return followMapper.selectActiveUserList(u_id);
	}

	@Override
	public List<FollowDTO> selectPassiveUserList(String f_passiveUser) {
		return followMapper.selectPassiveUserList(f_passiveUser);
	}

	@Override
	public void deleteUserFollow(String u_id) {
		followMapper.deleteUserFollow(u_id);
	}

	@Override
	public List<FollowDTO> followUnfollowList(String u_id) {
		return followMapper.followUnfollowList(u_id);
	}

	@Override
	public int followingCount(String u_id) {
		return followMapper.followingCount(u_id);
	}

	@Override
	public int followerCount(String u_id) {
		return followMapper.followerCount(u_id);
	}
}
