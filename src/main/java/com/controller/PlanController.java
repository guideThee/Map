package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.Bean.Point;
import com.Bean.User;
import com.Bean.Warehouse;
import com.controller.other.PrimaryKeyUtil;
import com.service.PlanService;
import com.service.PointService;
import com.service.WarehouseService;

import Bean.PlanArray;
import Bean.pointArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/PlanController")
public class PlanController {
	@Autowired
	private PlanService planService;
	@Autowired
	private PointService pointService;
	@Autowired
	private WarehouseService warehouseService;

	@RequestMapping(value = "savePlan", method = { RequestMethod.POST })
	@ResponseBody
	public void savePlan(@RequestBody String ss, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException {
		JSONObject obj = new JSONObject().fromObject(ss);
		System.out.println(ss);
		System.out.println("---------保存方案---------------");
		Plan plan = new Plan();
		plan.setPlanId(PrimaryKeyUtil.getPrimaryKey());
		plan.setUserLoginname(obj.getString("userName"));
		plan.setPlanName(obj.getString("planName"));
		plan.setPoint(obj.getString("point"));
		plan.setWarehouse(obj.getString("warehouse"));
		plan.setFlage(obj.getString("flage"));
		plan.setDistance(obj.getString("distance"));
		plan.setCreattime(new Date());
		System.out.println("-----------方案信息：----------------");
		System.out.println("PlanID:" + plan.getPlanId());
		System.out.println("UserID:" + plan.getUserLoginname());
		System.out.println("PlanName:" + plan.getPlanName());
		System.out.println("Point:" + plan.getPoint());
		System.out.println("Warehouse:" + plan.getWarehouse());
		System.out.println("Flage:" + plan.getFlage());
		System.out.println("CreatTime:" + plan.getCreattime());
		int m=planService.deleteByuserLoginnameAndplanName(plan);
		m =pointService.deleteByuserLoginnameAndplanName(plan);
		m=warehouseService.deleteByuserLoginnameAndplanName(plan);
		m= planService.addPlanByPlan(plan);
		session.setAttribute("PLAN", plan);
		String json = JSONArray.fromObject(plan).toString();
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);
		resp.getWriter().flush();
	}

	@RequestMapping(value = "getdata", method = { RequestMethod.GET })
	@ResponseBody
	public void getdata(@RequestBody String ss, HttpServletResponse resp, HttpServletRequest request,HttpSession session, Model model)
			throws ServletException, IOException {
		System.out.println("---------方案列表---------------");
		User user = (User) request.getSession().getAttribute("USER");
		Plan u=new Plan();
		u.setUserLoginname(user.getUserLoginname());
		List<Plan> plan = planService.queryAllPlan(u);
		List<PlanArray> planArray = new ArrayList<PlanArray>();
		for (int i = 0; i < plan.size(); i++) {
			PlanArray p = new PlanArray();
			p.setPlanName(plan.get(i).getPlanName());
			p.setPoint(plan.get(i).getPoint());
			p.setWarehouse(plan.get(i).getWarehouse());
			if (Integer.parseInt(plan.get(i).getFlage()) == 0 || Integer.parseInt(plan.get(i).getFlage()) == 2)
				p.setFlage("否");
			else
				p.setFlage("是");
			p.setCreattime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(plan.get(i).getCreattime()));
			p.setDistance(plan.get(i).getDistance() + "    公里");
			planArray.add(p);
		}
		String json = JSONArray.fromObject(planArray).toString();
		System.out.println(json);
		JSONObject obj = new JSONObject();
		obj.put("code", 0);
		obj.put("msg", "");
		obj.put("count", 1000);
		obj.put("data", planArray);
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(obj);
		resp.getWriter().flush();
	}

	@RequestMapping(value = "detail", method = { RequestMethod.POST })
	@ResponseBody
	public void detail(@RequestBody String ss, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException {
		System.out.println("---------读取方案---------------");
		JSONObject obj = new JSONObject().fromObject(ss);
		String userLoginname = obj.getString("userLoginname");
		String planName = obj.getString("planName");
		Plan p = new Plan();
		p.setUserLoginname(userLoginname);
		p.setPlanName(planName);
		Plan plan = planService.queryByuserLoginnameAndplanName(p);
		session.setAttribute("PLAN", plan);
		List<Point> points = pointService.queryByuserLoginnameAndplanName(p);
		List<Warehouse> Qwarehouse = warehouseService.queryByuserLoginnameAndplanName(p);
		List<Warehouse> warehouse = new ArrayList<Warehouse>();
		for (int i = 0; i < Qwarehouse.size(); i++) {
			Warehouse w = new Warehouse();
			for (int j = 0; j < Qwarehouse.size(); j++)
				if (Qwarehouse.get(j).getId() == (i + 1)) {
					w.setId(Qwarehouse.get(j).getId());
					w.setPlanName(Qwarehouse.get(j).getPlanName());
					w.setUserLoginname(Qwarehouse.get(j).getUserLoginname());
					w.setWarehouseId(Qwarehouse.get(j).getWarehouseId());
					w.setWarehouseName(Qwarehouse.get(j).getWarehouseName());
					w.setLat(Qwarehouse.get(j).getLat());
					w.setLng(Qwarehouse.get(j).getLng());
					warehouse.add(w);
					break;
				}
		}
		System.out.println("仓库ID：");
		for (int i = 0; i < warehouse.size(); i++) {
			System.out.println(warehouse.get(i).getId());
		}
		ArrayList list = new ArrayList<>();
		list.add(plan);
		list.add(points);
		list.add(warehouse);
		String json = JSONArray.fromObject(list).toString();
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);
		resp.getWriter().flush();
	}

	@RequestMapping(value = "delete", method = { RequestMethod.POST })
	@ResponseBody
	public void delete(@RequestBody String ss, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException {
		System.out.println("---------删除方案---------------");
		JSONObject obj = new JSONObject().fromObject(ss);
		String userLoginname = obj.getString("userLoginname");
		String planName = obj.getString("planName");
		Plan p = new Plan();
		p.setUserLoginname(userLoginname);
		p.setPlanName(planName);
		int m = planService.deleteByuserLoginnameAndplanName(p);
		m = pointService.deleteByuserLoginnameAndplanName(p);
		m = warehouseService.deleteByuserLoginnameAndplanName(p);
		String json = JSONArray.fromObject(m).toString();
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);
		resp.getWriter().flush();
	}

	@RequestMapping(value = "newPlan", method = { RequestMethod.POST })
	@ResponseBody
	public void newPlan(@RequestBody String ss, HttpServletResponse resp, HttpServletRequest request,
			HttpSession session, Model model) throws ServletException, IOException {
		System.out.println("---------新建方案---------------");
		User user = (User) request.getSession().getAttribute("USER");
		String userLoginname = user.getUserLoginname();
		String planName = ss;
		Plan p = new Plan();
		p.setUserLoginname(userLoginname);
		p.setPlanName(planName.substring(1, planName.length()-1));
		Plan plan= planService.queryByuserLoginnameAndplanName(p);
		System.out.println(plan);
		int m;
		if(plan==null)
		{
			m=1;
		}
		else
		{
			m=0;
		}
		String json = JSONArray.fromObject(m).toString();
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);
		resp.getWriter().flush();
	}
}
