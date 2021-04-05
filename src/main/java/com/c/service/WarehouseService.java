package com.service;

import java.util.List;

import com.bean.Plan;
import com.bean.Warehouse;

public interface WarehouseService {
	int addWarehouseBywarehouse(Warehouse warehouse);
	List<Warehouse> queryByuserLoginnameAndplanName(Plan plan);
	int deleteByuserLoginnameAndplanName(Plan plan);
}
