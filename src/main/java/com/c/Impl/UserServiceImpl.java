package com.c.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.User;
import com.dao.UserMapper;
import com.c.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	public int addUserByUser(User user) {

		return userMapper.insert(user);
	}

	public User queryUserByLoginNameAndPassword(User user) {
		return userMapper.queryUserByLoginNameAndPassword(user);
	}

	@Override
	public User queryUserByLoginName(User user) {
		return userMapper.queryUserByLoginName(user);
	}
}