package com.c.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bean.Plan;
import com.bean.Point;
import com.c.service.PlanService;

@ContextConfiguration(locations={"classpath:/spring-mybatis.xml","classpath:/spring-mvc.xml","classpath:/mybatis-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PlanServiceImplTest {
	@Autowired
	private PlanService planService;

	@Test
	public void testAddPlan() {
		List<Point> list = new ArrayList<>();
		Point point1 = new Point("test12",123,"1123","123","11");
		Point point2 = new Point("test12",1234,"1124","124","12");
		list.add(point1);
		list.add(point2);
		
		Plan plan = new Plan("test12","test1","name1","121",new Timestamp(112312323L),list);
		planService.addPlan(plan);
	}

	@Test
	public void testQueryAllPlan() {
		List<Plan> queryAllPlan = planService.queryAllPlan();
		for (Plan plan : queryAllPlan) {
			System.out.println(plan);
		}
	}

	@Test
	public void testQueryByUserLoginname() {
		List<Plan> queryAllPlan = planService.queryByUserLoginname("test3");
		for (Plan plan : queryAllPlan) {
			System.out.println(plan);
		}
	}

	@Test
	public void testDeleteByPlanId() {
		fail("Not yet implemented");
	}

}
