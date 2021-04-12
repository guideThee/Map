package com.c.service;

import java.util.List;

import com.bean.Plan;
import com.bean.Point;

public interface PlanService {
	
	/** 增加plan以及对应的point进入数据库
	 * @param plan
	 * @return 
	 */
	int addPlan(Plan plan);
	
	/**增加的points进入数据库
	 * @param points
	 * @return
	 */
	int addPoints(List<Point> points);
//	
    /**查询所有plan
     * @param plan
     * @return
     */
    List<Plan> queryAllPlan();
    
    /**
     * @param plan
     * @return 返回一个用户的所有plan
     */
    List<Plan> queryByUserLoginname(String userLoginname);
    
    /** 根据planId删除Plan，以及对应的points
     * @param planId
     * @return
     */
    int deletePointsByPlanId(String planId);
    
    /** 根据planId删除points
     * @param planId
     * @return
     */
    int deletePlanByPlanId(String planId);
    
    /**查询一个plan
     * @param planId
     * @return
     */
    Plan queryOnePlan(String planId);
    
    /**查询一个plan的所有点
     * @param planId
     * @return
     */
    List<Point> queryPointsInOnePlan(String planId);
    
    /**根据用户名和方案名查询方案
     * @param userLoginname
     * @param planName
     * @return
     */
    Plan queryByUserLoginnameAndPlanName(Plan plan);
    
}