package com.hong.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hong.dao.TestUserMapper;
import com.hong.model.TestUser;

@Service
public class TestService {

	@Resource
	private TestUserMapper testUserMapper;
		
	public void insertTest() {
		TestUser user=TestUser.builder().name("bean03").build();
		testUserMapper.insertSelective(user);
	}

	public void insertTestAjax(TestUser userVo) {
		testUserMapper.insertSelective(userVo);
	}
	
}
