package com.Dao;

import java.util.List;

import com.Bean.Plan;

public interface PlanMapper {
	int deleteByPrimaryKey(String planId);

	int insert(Plan record);

	int insertSelective(Plan record);

	Plan selectByPrimaryKey(String planId);

	int updateByPrimaryKeySelective(Plan record);

	int updateByPrimaryKey(Plan record);

	List<Plan> queryAllPlan(Plan plan);

	Plan queryByuserLoginnameAndplanName(Plan plan);

	int deleteByuserLoginnameAndplanName(Plan plan);
}