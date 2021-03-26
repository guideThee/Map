package com.test;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.Bean.Plan;
import com.Impl.PlanServiceImpl;
import com.service.PlanService;

//import static org.junit.Assert.*;
//
//import org.junit.Test;

@ContextConfiguration("/spring-mybatis.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestForPlanService {
	@Autowired
	private PlanService planService;

	@Test
	public void testAddPlanByPlan() {
		Plan plan = new Plan("username","planname","123","1","warehouse","flage",new Date(213123123123L));
		planService.addPlanByPlan(plan);
	}
	

//	@Test
	public void testQueryAllPlan() {
	}

//	@Test
	public void testQueryByuserLoginnameAndplanName() {
	}

//	@Test
	public void testDeleteByuserLoginnameAndplanName() {
	}

}
