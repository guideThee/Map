package com.dao;

import java.util.List;

import com.bean.Plan;
import com.bean.Point;

public interface PointMapper {
	int deleteByPlanId(String planId);

	int insertBatch(List<Point> points);

	List<Point> queryByPlanId(String planId);

}