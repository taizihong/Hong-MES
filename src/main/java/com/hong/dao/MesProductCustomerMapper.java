package com.hong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hong.beans.PageQuery;
import com.hong.dto.ProductDto;
import com.hong.dto.SearchProductDto;

public interface MesProductCustomerMapper {
	Long getProductCount();

	int countBySearchDto(@Param("dto") SearchProductDto dto);

	List<ProductDto> getPageListBySearchDto(@Param("dto") SearchProductDto dto, @Param("page") PageQuery page);

}