package com.c.controller;

import java.io.IOException;
import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.bean.Plan;
import com.bean.Point;
import com.bean.User;
import com.c.controller.other.PrimaryKeyUtil;
import com.c.service.PlanService;

import Bean.PlanArray;
import Bean.pointArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/PlanController")
public class PlanController {
	@Autowired
	private PlanService planService;
//	{
//	{Plan ...},
//	{大数组，里面存了多组point,每一组point里面存了point}
//	}
	@RequestMapping(value = "savePlan", method = { RequestMethod.POST })
	@ResponseBody
	public void savePlan(@RequestBody String ss, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException {
		Plan plan = JSON.parseObject(ss, Plan.class);
		User user = (User) session.getAttribute("USER");
		plan.setUserLoginname(user.getUserLoginname());
		System.out.println("plan:"+plan);
		//先删除之前的plan
		Plan oldPlan = planService.queryByUserLoginnameAndPlanName(plan);
		System.out.println("oldPlan:"+oldPlan);
		if(oldPlan != null) {
			planService.deletePlanByPlanId(oldPlan.getPlanId());
		}
		//加入新的plan
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(ss);
		System.out.println("---------保存方案---------------");
		plan.setPlanId(user.getUserLoginname().concat(timestamp.toString()));
		plan.setCreateTime(timestamp);
		System.out.println("-----------方案信息：----------------");
		System.out.println("PlanID:" + plan.getPlanId());
		System.out.println("UserID:" + plan.getUserLoginname());
		System.out.println("PlanName:" + plan.getPlanName());
		System.out.println("CreatTime:" + plan.getCreateTime());
		planService.addPlan(plan);
		//添加plan进入session中，方便savePoints的时候调用
		session.setAttribute("PLAN", plan);
		String json = JSONArray.fromObject(plan).toString();
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print("yes");
		resp.getWriter().flush();
	}
	
	@RequestMapping(value = "savePoint", method = { RequestMethod.POST })
	@ResponseBody
	public void savePoint(@RequestBody String ss, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException {
//		JSONObject obj = new JSONObject().fromObject(ss);
		JSONArray jsonArray = JSONArray.fromObject(ss);
//		JSONArray jsonArray = obj.getJSONArray("");
		System.out.println("jsonArray:"+jsonArray);
		List<Point> points = (List<Point>) JSONArray.toCollection(jsonArray, Point.class);
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(ss);
		System.out.println("---------保存方案---------------");
		User user = (User) session.getAttribute("USER");
		Plan plan = (Plan) session.getAttribute("PLAN");
		for (Point point : points) {
			point.setPlanId(plan.getPlanId());
		}
		planService.deletePointsByPlanId(plan.getPlanId());
		planService.addPoints(points);
		
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
//		resp.getWriter().print(json);
		resp.getWriter().print("yes");
		resp.getWriter().flush();
	}
	
	@RequestMapping(value = "getPlans")
	public String getPlans(HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException {
		User user = (User) session.getAttribute("USER");
		List<Plan> plans = planService.queryByUserLoginname(user.getUserLoginname());
		model.addAttribute("PLANS", plans);
		return "userPlans";
	}
	
	@RequestMapping(value = "loadPlan")
	public void loadPlan(@RequestParam String planId,HttpServletResponse resp, HttpSession session, Model model,RedirectAttributes attr)
			throws ServletException, IOException {
		System.out.println("loadPlan");
		User user = (User) session.getAttribute("USER");
		Plan plan = planService.queryOnePlan(planId);
		//将plan添加进入session，方便getPoints和getwarehousePoints调用。
		session.setAttribute("PLAN", plan);
		List<Point> points = planService.queryPointsInOnePlan(planId);
		List<Point> warehousePoints = new ArrayList<>();
		System.out.println("setAttributeLOADPLAN");
		session.setAttribute("LOADPLANNAME", plan.getPlanName());
		System.out.println(session.getAttribute("LOADPLANNAME"));
		//session.setAttribute("LOADPLAN", plan);这个操作没用，是因为之前已经插入了PLAN了吗
		//传给map.jsp一个planName信息
		//attr.addAttribute("planName", plan.getPlanName());
		resp.sendRedirect("/Map/map.jsp");
	}
	
	/**获得普通的标记点集
	 * @param ss
	 * @param resp
	 * @param session
	 * @param model
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "getPoints", method = { RequestMethod.POST })
	@ResponseBody
	public void getPoints(@RequestBody String ss, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException {
		
		User user = (User) session.getAttribute("USER");
		Plan plan = (Plan) session.getAttribute("PLAN");
		List<Point> points = planService.queryPointsInOnePlan(plan.getPlanId());
		List<Point> warehousePoints = new ArrayList<>();
		for (Point point : points) {
			System.out.println(point);
		}
		//分离仓库点集和标记点集
		for(int i = 0; i < points.size();i++) {
			Point point = points.get(i);
			if(point.getFlag().equals("1")) {
				warehousePoints.add(point);
				points.remove(i);
				i--;
			}
		}
		String json = JSONArray.fromObject(points).toString();
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);
//		resp.getWriter().print("yes");
		resp.getWriter().flush();
	}
	/**获得仓库点集
	 * @param ss
	 * @param resp
	 * @param session
	 * @param model
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "getWarehousePoints", method = { RequestMethod.POST })
	@ResponseBody
	public void getWarehousePoints(@RequestBody String ss, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException {
		
		User user = (User) session.getAttribute("USER");
		Plan plan = (Plan) session.getAttribute("PLAN");
		List<Point> points = planService.queryPointsInOnePlan(plan.getPlanId());
		List<Point> warehousePoints = new ArrayList<>();
		//分离仓库点集和标记点集
		for(int i = 0; i < points.size();i++) {
			Point point = points.get(i);
			if(point.getFlag().equals("1")) {
				System.out.println("pointFlag:"+point.getFlag());
				warehousePoints.add(point);
				points.remove(i);
				i--;
			}
		}
		System.out.println("warehousePoints:"+warehousePoints);
		for (Point point : warehousePoints) {
			System.out.println(point);
		}
		String json = JSONArray.fromObject(warehousePoints).toString();
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);
//		resp.getWriter().print("yes");
		resp.getWriter().flush();
	}
	
//	@RequestMapping(value = "getdata", method = { RequestMethod.GET })
//	@ResponseBody
//	public void getdata(@RequestBody String ss, HttpServletResponse resp, HttpServletRequest request,HttpSession session, Model model)
//			throws ServletException, IOException {
//		System.out.println("---------方案列表---------------");
//		User user = (User) request.getSession().getAttribute("USER");
//		Plan u=new Plan();
//		u.setUserLoginname(user.getUserLoginname());
//		List<Plan> plan = planService.queryAllPlan(u);
//		List<PlanArray> planArray = new ArrayList<PlanArray>();
//		for (int i = 0; i < plan.size(); i++) {
//			PlanArray p = new PlanArray();
//			p.setPlanName(plan.get(i).getPlanName());
//			p.setPoint(plan.get(i).getPoint());
//			p.setWarehouse(plan.get(i).getWarehouse());
//			if (Integer.parseInt(plan.get(i).getFlage()) == 0 || Integer.parseInt(plan.get(i).getFlage()) == 2)
//				p.setFlage("否");
//			else
//				p.setFlage("是");
//			p.setCreattime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(plan.get(i).getCreattime()));
//			p.setDistance(plan.get(i).getDistance() + "    公里");
//			planArray.add(p);
//		}
//		String json = JSONArray.fromObject(planArray).toString();
//		System.out.println(json);
//		JSONObject obj = new JSONObject();
//		obj.put("code", 0);
//		obj.put("msg", "");
//		obj.put("count", 1000);
//		obj.put("data", planArray);
//		resp.setContentType("text/json; charset=utf-8");
//		resp.getWriter().print(obj);
//		resp.getWriter().flush();
//	}
//
//	@RequestMapping(value = "detail", method = { RequestMethod.POST })
//	@ResponseBody
//	public void detail(@RequestBody String ss, HttpServletResponse resp, HttpSession session, Model model)
//			throws ServletException, IOException {
//		System.out.println("---------读取方案---------------");
//		JSONObject obj = new JSONObject().fromObject(ss);
//		String userLoginname = obj.getString("userLoginname");
//		String planName = obj.getString("planName");
//		Plan p = new Plan();
//		p.setUserLoginname(userLoginname);
//		p.setPlanName(planName);
//		Plan plan = planService.queryByuserLoginnameAndplanName(p);
//		session.setAttribute("PLAN", plan);
//		List<Point> points = pointService.queryByuserLoginnameAndplanName(p);
//		List<Warehouse> Qwarehouse = warehouseService.queryByuserLoginnameAndplanName(p);
//		List<Warehouse> warehouse = new ArrayList<Warehouse>();
//		for (int i = 0; i < Qwarehouse.size(); i++) {
//			Warehouse w = new Warehouse();
//			for (int j = 0; j < Qwarehouse.size(); j++)
//				if (Qwarehouse.get(j).getId() == (i + 1)) {
//					w.setId(Qwarehouse.get(j).getId());
//					w.setPlanName(Qwarehouse.get(j).getPlanName());
//					w.setUserLoginname(Qwarehouse.get(j).getUserLoginname());
//					w.setWarehouseId(Qwarehouse.get(j).getWarehouseId());
//					w.setWarehouseName(Qwarehouse.get(j).getWarehouseName());
//					w.setLat(Qwarehouse.get(j).getLat());
//					w.setLng(Qwarehouse.get(j).getLng());
//					warehouse.add(w);
//					break;
//				}
//		}
//		System.out.println("仓库ID：");
//		for (int i = 0; i < warehouse.size(); i++) {
//			System.out.println(warehouse.get(i).getId());
//		}
//		ArrayList list = new ArrayList<>();
//		list.add(plan);
//		list.add(points);
//		list.add(warehouse);
//		String json = JSONArray.fromObject(list).toString();
//		resp.setHeader("Cache-Control", "no-cache");
//		resp.setContentType("text/json; charset=utf-8");
//		resp.getWriter().print(json);
//		resp.getWriter().flush();
//	}
//
//	@RequestMapping(value = "delete", method = { RequestMethod.POST })
//	@ResponseBody
//	public void delete(@RequestBody String ss, HttpServletResponse resp, HttpSession session, Model model)
//			throws ServletException, IOException {
//		System.out.println("---------删除方案---------------");
//		JSONObject obj = new JSONObject().fromObject(ss);
//		String userLoginname = obj.getString("userLoginname");
//		String planName = obj.getString("planName");
//		Plan p = new Plan();
//		p.setUserLoginname(userLoginname);
//		p.setPlanName(planName);
//		int m = planService.deleteByuserLoginnameAndplanName(p);
//		m = pointService.deleteByuserLoginnameAndplanName(p);
//		m = warehouseService.deleteByuserLoginnameAndplanName(p);
//		String json = JSONArray.fromObject(m).toString();
//		resp.setHeader("Cache-Control", "no-cache");
//		resp.setContentType("text/json; charset=utf-8");
//		resp.getWriter().print(json);
//		resp.getWriter().flush();
//	}

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
//		Plan plan= planService.queryByuserLoginnameAndplanName(p);
//		System.out.println(plan);
		int m;
//		if(plan==null)
//		{
			m=1;
//		}
//		else
//		{
//			m=0;
//		}
		String json = JSONArray.fromObject(m).toString();
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);
		resp.getWriter().flush();
	}
}