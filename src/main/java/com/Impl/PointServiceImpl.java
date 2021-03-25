package com.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bean.Plan;
import com.Bean.Point;
import com.Dao.PointMapper;
import com.service.PointService;

@Service
public class PointServiceImpl implements PointService{
	@Autowired
	private PointMapper pointMapper;
	public int addPointByPoint(Point point) {
		return pointMapper.insert(point);
	}
	@Override
	public List<Point> queryByuserLoginnameAndplanName(Plan plan) {
		return pointMapper.queryByuserLoginnameAndplanName(plan);
	}
	@Override
	public int deleteByuserLoginnameAndplanName(Plan plan) {
		return pointMapper.deleteByuserLoginnameAndplanName(plan);
	}	

}
