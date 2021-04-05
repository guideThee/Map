package com.c.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.bean.Plan;
import com.dao.PlanMapper;

public class PlanMapperTest {
	
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	//success
	@Test
	public void deleteByPrimaryKey() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		PlanMapper mapper = openSession.getMapper(PlanMapper.class);
//		Plan plan = new Plan("test2","test1","name","12",new Timestamp(112312323L));
		int i = mapper.deleteByPrimaryKey("test2");
		System.out.println(i);
		openSession.commit();
	}
	
	//成功
	@Test
	public void insert() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		PlanMapper mapper = openSession.getMapper(PlanMapper.class);
		Plan plan = new Plan("test3","test3","name3","12",new Timestamp(112312323L));
		int i = mapper.insert(plan);
		System.out.println(i);
		openSession.commit();
		
	}
	@Test
	public void selectByPrimaryKey() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		PlanMapper mapper = openSession.getMapper(PlanMapper.class);
//		Plan plan = new Plan("test2","test1","name","12",new Timestamp(112312323L));
		Plan plan = mapper.selectByPrimaryKey("test2");
		System.out.println(plan);
		openSession.commit();
	}
	//success
	@Test
	public void updateByPrimaryKey() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		PlanMapper mapper = openSession.getMapper(PlanMapper.class);
		Plan plan = new Plan("test2","test3","name3","123",new Timestamp(112312323L));
		int i = mapper.updateByPrimaryKey(plan);
		System.out.println(i);
		openSession.commit();
	}
	//success
	@Test
	public void queryAllPlan() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		PlanMapper mapper = openSession.getMapper(PlanMapper.class);
//		Plan plan = new Plan("test3","test3","name3","12",new Timestamp(112312323L));
		List<Plan> plans = mapper.queryAllPlan();
		for (Plan plan : plans) {
			
			System.out.println(plan);
			
		}
		openSession.commit();
	}
	//success
	@Test
	public void queryByuserLoginname() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		PlanMapper mapper = openSession.getMapper(PlanMapper.class);
//		Plan plan = new Plan("test3","test3","name3","12",new Timestamp(112312323L));
		List<Plan> plans = mapper.queryByuserLoginname("test3");
		for (Plan plan : plans) {
			System.out.println(plan);
		}
		openSession.commit();
	}

}
