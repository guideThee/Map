package com.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Bean.User;
import com.controller.other.PrimaryKeyUtil;
import com.service.UserService;

import Bean.pointArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/UserController")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "resign", method = { RequestMethod.POST })
	public void resign(User user, HttpServletResponse resp,HttpServletRequest req,Model model, HttpSession session,RedirectAttributes attr) throws ServletException, IOException {
//		JSONObject obj = new JSONObject().fromObject(ss);
//		User user = new User();
		user.setUserId(PrimaryKeyUtil.getPrimaryKey());
//		user.setUserLoginname(obj.getString("UserLogin"));
//		user.setUserPassword(obj.getString("UserPassword"));
//		user.setUserName(obj.getString("UserName"));
//		user.setUserSex(obj.getString("UserSex"));
//		user.setUserTelephone(obj.getString("UserTelephone"));
//		System.out.println("-----------注册信息：----------------");
//		System.out.println("UserID:" + user.getUserId());
//		System.out.println("UserLogin:" + user.getUserLoginname());
//		System.out.println("Userpassword:" + user.getUserPassword());
//		System.out.println("Username:" + user.getUserName());
//		System.out.println("Usersex:" + user.getUserSex());
//		System.out.println("Usertelephone:" + user.getUserTelephone());
		User u = userService.queryUserByLoginName(user);
//		System.out.println(u);
//		int m;
		if (u == null) {
			userService.addUserByUser(user);
			session.setAttribute("USER", user);
			resp.sendRedirect("/Map/map.jsp");
		} else {
			attr.addAttribute("loginError", "用户已存在");
			resp.sendRedirect("/Map/regist.jsp");
//			m = 0;
		}
//		String json = JSONArray.fromObject(m).toString();
//		resp.setHeader("Cache-Control", "no-cache");
//		resp.setContentType("text/json; charset=utf-8");
//		resp.getWriter().print(json);
//		resp.getWriter().flush();
	}

	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public void login(HttpServletResponse resp,HttpServletRequest req,User user, HttpSession session, Model model,RedirectAttributes attr)
			throws ServletException, IOException {
		
//		User user = new User();
//		user.setUserLoginname(obj.getString("UserLogin"));
//		user.setUserPassword(obj.getString("UserPassword"));
//		System.out.println("-----------登录信息：----------------");
//		System.out.println("UserLogin:" + user.getUserLoginname());
//		System.out.println("Userpassword:" + user.getUserPassword());
		User queryUser = userService.queryUserByLoginNameAndPassword(user);
		if (queryUser != null) {
//			System.out.println("-----------查询结果：----------------");
//			System.out.println("UserID:" + u.getUserId());
//			System.out.println("UserLogin:" + u.getUserLoginname());
//			System.out.println("Userpassword:" + u.getUserPassword());
//			System.out.println("Username:" + u.getUserName());
//			System.out.println("Usersex:" + u.getUserSex());
//			System.out.println("Usertelephone:" + u.getUserTelephone());
			session.setAttribute("USER", queryUser);
			resp.sendRedirect("/Map/map.jsp");
		}else {
			attr.addAttribute("loginError", "用户名或密码错误!");
			resp.sendRedirect("/Map/login.jsp");
		}
	}
}
