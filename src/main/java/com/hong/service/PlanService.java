package com.hong.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.google.common.base.Preconditions;
import com.hong.beans.PageQuery;
import com.hong.beans.PageResult;
import com.hong.dao.MesOrderMapper;
import com.hong.exception.ParamException;
import com.hong.model.MesOrder;
import com.hong.util.BeanValidator;
import com.hong.util.MyStringUtils;


@Service
public class PlanService {
	@Resource
	private MesOrderMapper mesOrderMapper;

	@Resource
	private SqlSession sqlSession;

	public void startPlansByOrderIds(String[] idArray) {
		// TODO Auto-generated method stub
		
	}

	public void prePlan(MesOrder mesOrder) {
		// TODO Auto-generated method stub
		
	}
	
	//批量启动order后的批量plan启动
	

}
