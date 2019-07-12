package com.hong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hong.beans.PageQuery;
import com.hong.dto.SearchPlanDto;
import com.hong.model.MesPlan;

public interface MesPlanCustomerMapper {
	Long getPlanCount();

	// @Param("dto")--给mapper.xml查询sql指定参数名称 #{dto.keyword}
	int countBySearchDto(@Param("dto") SearchPlanDto dto);

	List<MesPlan> getPageListBySearchDto(@Param("dto") SearchPlanDto dto, @Param("page") PageQuery page);

	void batchStartWithIds(@Param("list") String[] tempIds);

}