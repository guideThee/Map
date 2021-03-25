package com.Dao;

import java.util.List;

import com.Bean.Plan;
import com.Bean.Point;

public interface PointMapper {
	int deleteByPrimaryKey(String pointId);

	int insert(Point record);

	int insertSelective(Point record);

	Point selectByPrimaryKey(String pointId);

	int updateByPrimaryKeySelective(Point record);

	int updateByPrimaryKey(Point record);

	List<Point> queryByuserLoginnameAndplanName(Plan plan);

	int deleteByuserLoginnameAndplanName(Plan plan);
}