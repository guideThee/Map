package com.dao;

import java.util.List;

import com.bean.Plan;

public interface PlanMapper {
	int deleteByPrimaryKey(String planId);

	int insert(Plan plan);

	Plan selectByPrimaryKey(String planId);

	int updateByPrimaryKey(Plan plan);

	List<Plan> queryAllPlan();

	List<Plan> queryByuserLoginname(String userLoginname);
	
	Plan selectByUserLoginnameAndplanName(Plan plan);

}