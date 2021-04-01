package com.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Plan;
import com.dao.PlanMapper;
import com.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private PlanMapper planMapper;
	public int addPlanByPlan(Plan plan) {
		return planMapper.insert(plan);
	}
	public List<Plan> queryAllPlan(Plan plan) {
		return planMapper.queryAllPlan(plan);
	}
	public Plan queryByuserLoginnameAndplanName(Plan plan) {
		return planMapper.queryByuserLoginnameAndplanName(plan);
	}
	@Override
	public int deleteByuserLoginnameAndplanName(Plan plan) {
		return planMapper.deleteByuserLoginnameAndplanName(plan);
	}
}
