package com.service;

import java.util.List;

import com.bean.Plan;
import com.bean.Point;

public interface PointService {
	int addPointByPoint(Point point);
	List<Point> queryByuserLoginnameAndplanName(Plan plan);
	int deleteByuserLoginnameAndplanName(Plan plan);
}
