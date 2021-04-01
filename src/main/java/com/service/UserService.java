package com.service;

import java.util.List;

import com.bean.User;

public interface UserService {
	int addUserByUser(User user);

	User queryUserByLoginNameAndPassword(User user);
	User queryUserByLoginName(User user);
}
