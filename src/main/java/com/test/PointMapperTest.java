package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bean.Plan;
import com.bean.Point;
import com.dao.PlanMapper;
import com.dao.PointMapper;

public class PointMapperTest {
	
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	private PointMapper mapper;
	
	@Before
	public void init() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		sqlSession = sqlSessionFactory.openSession();
		mapper = sqlSession.getMapper(PointMapper.class);
	}
	
	@After
	public void commit() {
		sqlSession.commit();
	}
	
	//success
	@Test
	public void deleteByPlanId() throws IOException {
//		Plan plan = new Plan("test2","test1","name","12",new Timestamp(112312323L));
		int i = mapper.deleteByPlanId("planId123");
		System.out.println(i);
	}
	
	//success
	@Test
	public void insertBatch() throws IOException {
		List<Point> list = new ArrayList<>();
		Point point1 = new Point("planId123",123,"123","123","2");
		Point point2 = new Point("planId1234",1234,"124","124","5");
		list.add(point1);
		list.add(point2);
		int i = mapper.insertBatch(list);
		System.out.println(i);
		
	}
	
	@Test
	public void queryByPlanId() throws IOException {
		List<Point> queryByPlanId = mapper.queryByPlanId("planId1234");
		for (Point point : queryByPlanId) {
			System.out.println(point);
		}
	}
}
	