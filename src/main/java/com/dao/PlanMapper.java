package com.dao;

import java.util.List;

import com.bean.Plan;

public interface PlanMapper {
	int deleteByPrimaryKey(String planId);

	int insert(Plan plan);

	Plan selectByPrimaryKey(String planId);

	int updateByPrimaryKey(Plan plan);

	List<Plan> queryAllPlan(Plan plan);

	List<Plan> queryByuserLoginname(Plan plan);

}