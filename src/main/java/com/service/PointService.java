package com.service;

import java.util.List;

import com.Bean.Plan;
import com.Bean.Point;

public interface PointService {
	int addPointByPoint(Point point);
	List<Point> queryByuserLoginnameAndplanName(Plan plan);
	int deleteByuserLoginnameAndplanName(Plan plan);
}
