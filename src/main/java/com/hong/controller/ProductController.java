package com.hong.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hong.beans.PageQuery;
import com.hong.beans.PageResult;
import com.hong.common.JsonData;
import com.hong.common.SameUrlData;
import com.hong.dto.ProductDto;
import com.hong.param.MesProductVo;
import com.hong.param.SearchProductParam;
import com.hong.service.ProductService;



@Controller
@RequestMapping("/product")
public class ProductController {

	private static String FPATH="product/";
	
	@Resource
	private ProductService productService;
	
	//增加材料页面
	@RequestMapping("/productinsert.page")
	public String productInsertPage() {
		return FPATH+"productinsert";
	}
	
	//增加材料功能
	@RequestMapping("/insert.json")
	@SameUrlData//防止重复提交
	public String insert(MesProductVo mesproductVo) {
		productService.insert(mesproductVo);
		return FPATH+"/product";
	}
	
	
	//批量到库
	@RequestMapping("/product.page")
	public String productList() {
		return FPATH+"product";
	}
	
	//批量到库 分页
	@RequestMapping("/product.json")
	@ResponseBody
    public JsonData searchPage(SearchProductParam param, PageQuery page) {
    	PageResult<ProductDto> pr=(PageResult<ProductDto>) productService.searchPageList(param, page);
    	return JsonData.success(pr);
    }
	
	//到库查询 修改
	@RequestMapping("/update.json")
	@ResponseBody
	public JsonData update(MesProductVo productVo) {
		productService.update(productVo);
		return JsonData.success(true);
	}
	
	//材料批量到库启动
	@RequestMapping("/productBatchStart.json")
    public String productBatchStart(String ids) {
		productService.batchStart(ids);
		return FPATH+"product";
    }
	
	//到库查询
	@RequestMapping("/productCome.page")
	public String productComePage() {
		return FPATH+"productCome";
	}
	
	//钢锭查询
	@RequestMapping("/productIron.page")
	public String productIronList() {
		return FPATH+"productIron";
	}
	
}
