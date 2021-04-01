package com.test;

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
	@Test
	public void deleteByPrimaryKey() throws IOException {

	}
	@Test
	public void insert() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession openSession = sqlSessionFactory.openSession();
		PlanMapper mapper = openSession.getMapper(PlanMapper.class);
		Plan plan = new Plan("test2","test1","name","12",new Timestamp(112312323L));
		int i = mapper.insert(plan);
		System.out.println(i);
		openSession.commit();
		
	}

	public void selectByPrimaryKey() {
		
	}

	public void updateByPrimaryKey() {
		
	}

	public void queryAllPlan(){
		
	}

	public void queryByuserLoginname() {
		
	}

}
