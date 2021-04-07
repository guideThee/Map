package com.c.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Plan;
import com.bean.Point;
import com.c.service.PlanService;
import com.dao.PlanMapper;
import com.dao.PointMapper;

@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private PlanMapper planMapper;
	
	@Autowired
	private PointMapper pointMapper;

	@Override
	public int addPlan(Plan plan) {
		
		return planMapper.insert(plan);
	}

	@Override
	public List<Plan> queryAllPlan() {
		return planMapper.queryAllPlan();
	}

	@Override
	public List<Plan> queryByUserLoginname(String userLoginname) {
		return planMapper.queryByuserLoginname(userLoginname);
	}

	@Override
	public int deletePointsByPlanId(String planId) {
		return pointMapper.deleteByPlanId(planId);
	}
	
	@Override
	public int deletePlanByPlanId(String planId) {
		return planMapper.deleteByPrimaryKey(planId);
	}


	@Override
	public Plan queryOnePlan(String planId) {
		return planMapper.selectByPrimaryKey(planId);
	}

	@Override
	public List<Point> queryPointsInOnePlan(String planId) {
		return pointMapper.queryByPlanId(planId);
	}


	@Override
	public int addPoints(List<Point> points) {
		return pointMapper.insertBatch(points);
	}

	@Override
	public Plan queryByUserLoginnameAndPlanName(Plan plan) {
		return planMapper.selectByUserLoginnameAndplanName(plan);
	}
}
