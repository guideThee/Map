package com.service;

import java.util.List;

import com.Bean.Plan;

public interface PlanService {
	int addPlanByPlan(Plan plan);
    List<Plan> queryAllPlan(Plan plan);
    Plan queryByuserLoginnameAndplanName(Plan plan);
    int deleteByuserLoginnameAndplanName(Plan plan);
}
