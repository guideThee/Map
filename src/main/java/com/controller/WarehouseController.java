package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Bean.Plan;
import com.Bean.User;
import com.Bean.Warehouse;
import com.controller.other.PrimaryKeyUtil;
import com.service.UserService;
import com.service.WarehouseService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/WarehouseController")
public class WarehouseController {
	@Autowired
	private WarehouseService warehouseService;

	@RequestMapping(value = "saveWarehouse", method = { RequestMethod.POST })
	@ResponseBody
	public void saveWarehouse(@RequestBody String ss, HttpServletResponse resp, HttpServletRequest request,
			HttpSession session, Model model) throws ServletException, IOException {
		System.out.println("--------JSON字符串-----------");
		System.out.println(ss);
		JSONArray jsonArray = JSONArray.fromObject(ss);
		User user = (User) request.getSession().getAttribute("USER");
		System.out.println(user.getUserLoginname());
		Plan plan = (Plan) request.getSession().getAttribute("PLAN");
		System.out.println(plan.getPlanName());
		for (int i = 0; i < jsonArray.size(); i++) {
			Warehouse warehouse = new Warehouse();
			warehouse.setWarehouseId(PrimaryKeyUtil.getPrimaryKey());
			warehouse.setPlanName(plan.getPlanName());
			warehouse.setUserLoginname(user.getUserLoginname());
			warehouse.setId(jsonArray.getJSONObject(i).getInt("id"));
			warehouse.setLng(jsonArray.getJSONObject(i).getString("lng"));
			warehouse.setLat(jsonArray.getJSONObject(i).getString("lat"));
			warehouse.setWarehouseName("仓库" + jsonArray.getJSONObject(i).getString("id"));
			int m = warehouseService.addWarehouseBywarehouse(warehouse);
		}
		String json = JSONArray.fromObject(plan).toString();
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);
		resp.getWriter().flush();
	}

}
