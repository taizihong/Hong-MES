package com.hong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hong.beans.PageQuery;
import com.hong.dto.ProductDto;
import com.hong.dto.SearchProductDto;
import com.hong.model.MesProduct;



public interface MesProductCustomerMapper {
	Long getProductCount();

	int countBySearchDto(@Param("dto") SearchProductDto dto);

	List<ProductDto> getPageListBySearchDto(@Param("dto") SearchProductDto dto, @Param("page") PageQuery page);

	
	int countBySearchBindListDto(@Param("dto") SearchProductDto dto);
	
	List<ProductDto> getPageListBySearchBindListDto(@Param("dto") SearchProductDto dto, @Param("page") PageQuery page);

	
	int countBySearchChildBindListDto(@Param("dto") SearchProductDto dto);

	List<ProductDto> getPageListBySearchChildBindListDto(@Param("dto") SearchProductDto dto, @Param("page") PageQuery page);

	
	int countBySearchParentBindListDto(@Param("dto") SearchProductDto dto);

	List<ProductDto> getPageListBySearchParentBindListDto(@Param("dto") SearchProductDto dto,@Param("page") PageQuery page);

	int childCounts(@Param("parentId")Integer parentId);

	List<MesProduct> childs(@Param("parentId")Integer parentId);
	

}