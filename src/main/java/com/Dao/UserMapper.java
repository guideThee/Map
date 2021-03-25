package com.Dao;

import com.Bean.User;

public interface UserMapper {
	int deleteByPrimaryKey(String userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(String userId);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User queryUserByLoginNameAndPassword(User user);

	User queryUserByLoginName(User user);
}