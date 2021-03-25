package com.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import com.mathworks.toolbox.javabuilder.*;
//import ACOOPT.*;
import Bean.pointArray;
import KMeans.Kcluster;
import net.sf.json.JSONArray;

@Controller
public class ajax {

	/**
	 * 1. 使用RequestMapping注解来映射请求的URL 2. 返回值会通过视图解析器解析为实际的物理视图,
	 * 对于InternalResourceViewResolver视图解析器，会做如下解析 通过prefix+returnVal+suffix
	 * 这样的方式得到实际的物理视图，然后会转发操作 "/WEB-INF/views/success.jsp"
	 * 
	 * @return
	 */
	public void outtxt(ArrayList list) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("E:/TEST.txt");// 创建文本文件
			int i = 0;
			pointArray point = new pointArray();
			while (list != null && i < list.size()) {// 循环写入
				point = (pointArray) list.get(i);
				System.out.println("id = " + point.getId());
				fileWriter.write(String.valueOf(point.getId()) + "    ");
				fileWriter.write(String.valueOf(point.getLng()) + "    ");
				fileWriter.write(String.valueOf(point.getLat()) + "    ");
				fileWriter.write("\r\n");// 写入 \r\n换行
				i++;
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@RequestMapping(value = "getdata", method = { RequestMethod.POST })
//	@ResponseBody
//	public void saveu(@RequestBody String ss, HttpServletResponse resp) throws ServletException, IOException {
//		/**/
//		System.out.println("--------------------");
//		System.out.println(ss);
//		JSONArray jsonArray = JSONArray.fromObject(ss);
//		ArrayList point = new ArrayList<pointArray>();
//		for (int i = 0; i < jsonArray.size(); i++) {
//			pointArray p = new pointArray();
//			p.setId(jsonArray.getJSONObject(i).getInt("id"));
//			p.setLng(Double.parseDouble(jsonArray.getJSONObject(i).getString("lng")));
//			p.setLat(Double.parseDouble(jsonArray.getJSONObject(i).getString("lat")));
//			point.add(p);
//		}
//		for (int i = 0; i < point.size(); i++) {
//			pointArray p = (pointArray) point.get(i);
//			System.out.println("id = " + p.getId());
//			System.out.println("lng = " + p.getLng());
//			System.out.println("lat = " + p.getLat());
//		}
//		outtxt(point);
//		try {
//			AcoOptTest test = new AcoOptTest();
//			Object[] result = test.tanxin3opt(1);
//			MWNumericArray output = null; // 用于保存输出矩阵
//			output = (MWNumericArray) result[0];
//			int[] res = output.getIntData();
//			for (int i = 0; i < res.length; i++)
//				System.out.print(res[i] + "----");
//			String json = JSONArray.fromObject(res).toString();
//			resp.setHeader("Cache-Control", "no-cache");
//			resp.setContentType("text/json; charset=utf-8");
//			resp.getWriter().print(json);
//			resp.getWriter().flush();
//		} catch (MWException e) {
//
//			e.printStackTrace();
//		}
//	}

	@RequestMapping(value = "generate", method = { RequestMethod.POST })
	@ResponseBody
	public void generate(@RequestBody String ss, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("--------------------");
		int x = ss.lastIndexOf(']');
		String s1 = ss.substring(0, x + 1);// 点集合Json
		System.out.println("点集合Json:" + s1);
		String s2 = ss.substring(x + 1, ss.length());
		int n = Integer.valueOf(s2).intValue(); // 聚类中心个数
		System.out.println("聚类中心个数:" + n);
		JSONArray jsonArray = JSONArray.fromObject(s1);
		ArrayList point = new ArrayList<pointArray>();// 点集合
		for (int i = 0; i < jsonArray.size(); i++) {
			pointArray p = new pointArray();
			p.setId(jsonArray.getJSONObject(i).getInt("id"));
			p.setLng(Double.parseDouble(jsonArray.getJSONObject(i).getString("lng")));
			p.setLat(Double.parseDouble(jsonArray.getJSONObject(i).getString("lat")));
			point.add(p);
		}
		pointArray[] points = new pointArray[point.size()];// 点数组
		pointArray[] center = new pointArray[n];// 聚类中心数组
		System.out.println("points:");
		for (int i = 0; i < point.size(); i++) {
			points[i] = (pointArray) point.get(i);
			System.out.println("id = " + points[i].getId());
			System.out.println("lng = " + points[i].getLng());
			System.out.println("lat = " + points[i].getLat());
		}

		Kcluster kmean = new Kcluster();
		kmean.productpoint(points, n);
		kmean.movecore(center);
		System.out.println("聚类中心:");
		for (int i = 0; i < center.length; i++) {
			System.out.println("Lng = " + center[i].getLng());
			System.out.println("Lat = " + center[i].getLat());
			System.out.println("flage = " + center[i].flage);
		}
		System.out.println("点数组分类:");
		for (int i = 0; i < points.length; i++) {
			System.out.println("id = " + points[i].getId());
			System.out.println("flage = " + points[i].flage);
		}
		ArrayList cluster = new ArrayList<ArrayList<pointArray>>();
		for (int i = 0; i < n; i++) {
			ArrayList ps = new ArrayList<pointArray>();
			for (int j = 0; j < point.size(); j++) {
				if (points[j].flage == i + 1) {
					ps.add(points[j]);
				}
			}
			center[i].setId(0);
			ps.add(center[i]);
			cluster.add(ps);
		}
		for (int i = 0; i < n; i++) {
			System.out.println("flage:" + (i + 1));
			ArrayList test = (ArrayList) cluster.get(i);
			for (int j = 0; j < test.size(); j++) {
				pointArray p = (pointArray) test.get(j);
				System.out.println(p.getId());
			}
		}
		String json = JSONArray.fromObject(cluster).toString();
		System.out.println(json);
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);
		resp.getWriter().flush();
	}
	@RequestMapping(value = "center", method = { RequestMethod.POST })
	@ResponseBody
	public void center(@RequestBody String ss, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("-------生成聚类中心----------");
		JSONArray jsonArray = JSONArray.fromObject(ss);
		ArrayList point = new ArrayList<pointArray>();// 点集合
		for (int i = 0; i < jsonArray.size(); i++) {
			pointArray p = new pointArray();
			p.setId(jsonArray.getJSONObject(i).getInt("id"));
			p.setLng(Double.parseDouble(jsonArray.getJSONObject(i).getString("lng")));
			p.setLat(Double.parseDouble(jsonArray.getJSONObject(i).getString("lat")));
			point.add(p);
		}		
		pointArray[] points = new pointArray[point.size()];// 点数组
		pointArray[] center = new pointArray[1];// 聚类中心数组
		System.out.println("points:");
		for (int i = 0; i < point.size(); i++) {
			points[i] = (pointArray) point.get(i);
			System.out.println("id = " + points[i].getId());
			System.out.println("lng = " + points[i].getLng());
			System.out.println("lat = " + points[i].getLat());
		}
		Kcluster kmean = new Kcluster();
		kmean.productpoint(points, 1);
		kmean.movecore(center);
		center[0].setId(0);
		System.out.println("聚类中心:");
		System.out.println("id:"+center[0].getId());
		System.out.println("lng:"+center[0].getLng());
		System.out.println("lat:"+center[0].getLat());
		String json = JSONArray.fromObject(center).toString();
		System.out.println(json);
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		resp.getWriter().print(json);
		resp.getWriter().flush();
	}
}