package com.service;

import java.util.List;

import com.Bean.Plan;
import com.Bean.Warehouse;

public interface WarehouseService {
	int addWarehouseBywarehouse(Warehouse warehouse);
	List<Warehouse> queryByuserLoginnameAndplanName(Plan plan);
	int deleteByuserLoginnameAndplanName(Plan plan);
}
