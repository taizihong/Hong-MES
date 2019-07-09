package com.hong.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.hong.beans.PageQuery;
//import com.hong.dao.MesOrderCustomerMapper;
import com.hong.dao.TestUserMapper;
//import com.hong.dto.SearchOrderDto;
//import com.hong.model.MesOrder;
import com.hong.model.TestUser;

public class Spring_MybatisTest {
	
	
	private static ApplicationContext bean=new ClassPathXmlApplicationContext("spring\\applicationContext.xml");
	private TestUserMapper testUserMapper;
//	private MesOrderCustomerMapper mesOrderCustomerMapper;
	@Test
	public void testMapper() {
		testUserMapper=bean.getBean(TestUserMapper.class);
		TestUser user=TestUser.builder().name("bean01").build();
		testUserMapper.insertSelective(user);
	}
	
//	@Test 
//	public void OrderMapperTest() {
//		mesOrderCustomerMapper=bean.getBean(MesOrderCustomerMapper.class);
//		PageQuery pq=new PageQuery();
//		pq.setPageNo(1);
//		pq.setPageSize(10);
//		pq.setOffset(0);
//		SearchOrderDto srd=new  SearchOrderDto();
//		srd.setSearch_status(1);
//		int total=mesOrderCustomerMapper.countBySearchDto(srd);
//		System.out.println("-->+total");
//		List<MesOrder> orderList = mesOrderCustomerMapper.getPageListBySearchDto(srd,pq);
//		for(MesOrder order:orderList) {
//			System.out.println(order);
//			
//	    }
//	}
//	
	
	
}
