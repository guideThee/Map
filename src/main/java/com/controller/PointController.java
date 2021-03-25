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
import com.Bean.Point;
import com.Bean.User;
import com.controller.other.PrimaryKeyUtil;
import com.service.PointService;
import Bean.pointArray;
import net.sf.json.JSONArray;

@Controller
@RequestMapping("/PointController")
public class PointController {
	@Autowired
	private PointService pointService;

	@RequestMapping(value = "savePoint", method = { RequestMethod.POST })
	@ResponseBody
	public void savePoint(@RequestBody String ss, HttpServletResponse resp, HttpServletRequest request,
			HttpSession session, Model model) throws ServletException, IOException {
		System.out.println("--------JSON字符串-----------");
		System.out.println(ss);
		JSONArray jsonArray = JSONArray.fromObject(ss);
		User user = (User) request.getSession().getAttribute("USER");
		System.out.println(user.getUserLoginname());
		Plan plan = (Plan) request.getSession().getAttribute("PLAN");
		System.out.println(plan.getPlanName());
		for (int i = 0; i < jsonArray.size(); i++) {
			Point p = new Point();
			p.setPointId(PrimaryKeyUtil.getPrimaryKey());
			p.setId(jsonArray.getJSONObject(i).getInt("id"));
			p.setLng(jsonArray.getJSONObject(i).getString("lng"));
			p.setLat(jsonArray.getJSONObject(i).getString("lat"));
			p.setFlage(jsonArray.getJSONObject(i).getInt("flage"));
			p.setPlanName(plan.getPlanName());
			p.setUserLoginname(user.getUserLoginname());
			System.out.println("添加point"+(i+1));
			int m=pointService.addPointByPoint(p);
		}
		String json = JSONArray.fromObject(plan).toString();
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);
		resp.getWriter().flush();		
	}
}
