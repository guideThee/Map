package com.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bean.Plan;
import com.Bean.Warehouse;
import com.Dao.WarehouseMapper;
import com.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService{
	@Autowired
	private WarehouseMapper warehouseMapper;

	public int addWarehouseBywarehouse(Warehouse warehouse) {
		return warehouseMapper.insert(warehouse);
	}

	@Override
	public List<Warehouse> queryByuserLoginnameAndplanName(Plan plan) {
		return warehouseMapper.queryByuserLoginnameAndplanName(plan);
	}

	@Override
	public int deleteByuserLoginnameAndplanName(Plan plan) {
		return warehouseMapper.deleteByuserLoginnameAndplanName(plan);
	}
}
