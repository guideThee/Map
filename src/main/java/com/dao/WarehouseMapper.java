package com.dao;

import java.util.List;

import com.bean.Plan;
import com.bean.Warehouse;

public interface WarehouseMapper {
	int deleteByPrimaryKey(String warehouseId);

	int insert(Warehouse record);

	int insertSelective(Warehouse record);

	Warehouse selectByPrimaryKey(String warehouseId);

	int updateByPrimaryKeySelective(Warehouse record);

	int updateByPrimaryKey(Warehouse record);

	List<Warehouse> queryByuserLoginnameAndplanName(Plan plan);

	int deleteByuserLoginnameAndplanName(Plan plan);
}