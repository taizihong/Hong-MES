package com.hong.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hong.beans.PageQuery;
import com.hong.beans.PageResult;
import com.hong.common.JsonData;
import com.hong.common.SameUrlData;
import com.hong.dto.ProductDto;
import com.hong.model.MesProduct;
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
	public JsonData update(MesProductVo mesproductVo) {
		productService.update(mesproductVo);
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
	
	//材料绑定列表
	@RequestMapping("/productBindList.page")
	public String productBindList() {
		return FPATH+"productBindList";
	}
	//绑定钢材分页
	@RequestMapping("/productBindList.json")
	@ResponseBody
    public JsonData searchBindListPage(SearchProductParam param, PageQuery page) {
    	PageResult<ProductDto> pr=(PageResult<ProductDto>) productService.searchPageBindList(param, page);
    	return JsonData.success(pr);
    }
	
	//待绑定钢材页面跳转
	@RequestMapping("/productBind.page")
	public String productBindPage(String id,Model model) {
		MesProduct mesProduct=productService.selectById(id);
		if(mesProduct!=null) {
			model.addAttribute("product",mesProduct);
			return FPATH+"productBind";
		}else {
			return FPATH+"productBindList";
		}
		
	}
	
	//绑定材料分页
	@RequestMapping("/productChildBindList.json")
	@ResponseBody
	public JsonData searchChildBindListPage(SearchProductParam param,PageQuery page) {
		PageResult<ProductDto> pro= (PageResult<ProductDto>)productService.searchPageChildBindList(param,page);
		return JsonData.success(pro);
	}
	
	//绑定逻辑
	@RequestMapping("/bind.json")
	@ResponseBody
	public JsonData bind(String parentId,String childId) {
		productService.bind(parentId,childId);
    	return JsonData.success(true);
	}
	//查看钢材已绑定的钢材
	@RequestMapping("/productParentBindList.json")
	@ResponseBody
	public JsonData searchParentBindListPage(SearchProductParam param, PageQuery page) {
		PageResult<ProductDto> pro=(PageResult<ProductDto>)productService.searchPageParentBindList(param, page);
		return JsonData.success(pro);
	}
	//钢材解绑钢锭
	@RequestMapping("/parentUnbound.json")
	@ResponseBody
    public JsonData unbound(String childId) {
    	boolean result=productService.unbound(childId);
    	return JsonData.success(result);
    }
	
	
	
}
