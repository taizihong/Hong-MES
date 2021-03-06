package com.hong.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.hong.dao.MesStockMapper;
import com.hong.model.MesOrder;
import com.hong.model.MesStock;
import com.hong.beans.PageQuery;
import com.hong.beans.PageResult;
import com.hong.dao.MesOrderMapper;
import com.hong.dao.MesProductCustomerMapper;
import com.hong.dao.MesProductMapper;
import com.hong.dto.ProductDto;
import com.hong.dto.SearchProductDto;
import com.hong.exception.SysMineException;
import com.hong.model.MesProduct;
import com.hong.param.MesProductVo;
import com.hong.param.SearchProductParam;
import com.hong.util.BeanValidator;

@Service
public class ProductService {

	@Resource
	private MesProductMapper mesProdcutMapper;
	@Resource
	private MesOrderMapper mesOrderMapper;

	@Resource
	private MesStockMapper messtockMapper;
	@Resource
	private MesProductCustomerMapper mesProductCustomerMapper;

	@Resource
	private SqlSession sqlSession;
	// 定义一个ID生成器
	private IdGenerator ig = new IdGenerator();

	// 批量到库启动
//	public void batchStart(String ids) {
//		if(StringUtils.isNotEmpty(ids)) {
//			MesProductMapper mesProductMapper=sqlSession.getMapper(MesProductMapper.class);
//			String[] idStrs=ids.split("&");
//			for(String id:idStrs) {
//				MesProduct mesProduct=mesProductMapper.selectByPrimaryKey(Integer.parseInt(id));
//				mesProduct.setProductStatus(1);
//				mesProduct.setProductOperateTime(new Date());
//				mesProductMapper.updateByPrimaryKeySelective(mesProduct);
//				//TODO
//				//生成待入库记录--原料库
//				batchStockPre(Integer.parseInt(id),mesProductMapper);
//			}
//		}
//	}
	public void batchStart(String ids) {
		if (StringUtils.isNotEmpty(ids)) {
			MesProductMapper mesProductMapper = sqlSession.getMapper(MesProductMapper.class);
			String[] idStrs = ids.split("&");
			for (String id : idStrs) {
				MesProduct mesProduct = mesProductMapper.selectByPrimaryKey(Integer.parseInt(id));
				mesProduct.setProductStatus(1);
				mesProduct.setProductOperateTime(new Date());
				mesProductMapper.updateByPrimaryKeySelective(mesProduct);
				// TODO
				batchStockPre(Integer.parseInt(id), mesProductMapper);
			}
		}
	}

//	public void productInsert(MesProductVo mesProductVo) {
//		// TODO Auto-generated method stub
//		// 校验
//		BeanValidator.check(mesProductVo);
//		// 获取增加个数
//
//		Integer counts = mesProductVo.getCount();
//		
//		List<String> ids=createProductIdsDefault(Long.valueOf(counts));
//		
//		MesProductMapper mesProductMapper=sqlSession.getMapper(MesProductMapper.class);
//		try {
//			for(String productid:ids) {
//				MesProduct product = MesProduct.builder().productId(productid)//
//						.productTargetweight(productVo.getProductTargetweight())//
//						.productRealweight(productVo.getProductRealweight())//
//						.productLeftweight(productVo.getProductLeftweight())//
//						.productBakweight(productVo.getProductLeftweight())//
//						.productIrontype(productVo.getProductIrontype())//
//						.productIrontypeweight(productVo.getProductIrontypeweight())//
//						.productMaterialname(productVo.getProductMaterialname())//
//						.productImgid(productVo.getProductImgid())//
//						.productLuhao(productVo.getProductLuhao())//
//						.productMaterialsource(productVo.getProductMaterialsource())//
//						.productStatus(productVo.getProductStatus())//
//						.productRemark(productVo.getProductRemark()).build();
//				product.setProductOperateIp("127.0.0.1");
//				product.setProductOperateTime(new Date());
//				product.setProductOperator("user");
//				tapProductMapper.insertSelective(product);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new SysMineException("创建过程问题");
//		}
//	}

	// 批量待入库操作-默认进入原料库
	private void batchStockPre(Integer id, MesProductMapper mesProductMapper) {
		if (id != null) {
			// 生成库存逻辑
			// 增加操作
			MesProduct mesProduct = mesProductMapper.selectByPrimaryKey(id);
			// Order*
			if (mesProduct != null) {
				MesStock mesStock = MesStock.builder().stockProductid(mesProduct.getId())//
						.stockImgid(mesProduct.getProductImgid())//
						.stockProductsource(mesProduct.getProductMaterialsource())//
						.stockStatus(1)//
						.stockStorageid(1).build();// 1 代表原料库

				Integer orderid = mesProduct.getProductOrderid();
				if (orderid != null) {
					MesOrder mesOrder = mesOrderMapper.selectByPrimaryKey(mesProduct.getProductOrderid());
					if (mesOrder != null) {
						mesStock.setStockProductname(mesOrder.getOrderProductname());
						mesStock.setStockOrderid(orderid);
						mesStock.setStockOrdername(mesOrder.getOrderProductname());
					}
				}
				// mesStock.setStockProductname(product.getProductMaterialname());
				mesStock.setStockStoragestatus(1);// 待入库
				messtockMapper.insertSelective(mesStock);
			}
		}
	}

	public void insert(MesProductVo mesproductVo) {
		// 校验
		BeanValidator.check(mesproductVo);
		// 获取增加个数
		Integer counts = mesproductVo.getCounts();
		List<String> ids = createProductIdsDefault(Long.valueOf(counts));

		MesProductMapper mesProductMapper = sqlSession.getMapper(MesProductMapper.class);
		try {
			for (String productid : ids) {
				// 批量增加-productDto
				MesProduct pd = MesProduct.builder().productId(productid)//
						.productTargetweight(mesproductVo.getProductTargetweight())//
						.productRealweight(mesproductVo.getProductRealweight())//
						.productLeftweight(mesproductVo.getProductLeftweight())//
						.productBakweight(mesproductVo.getProductLeftweight())//
						.productIrontype(mesproductVo.getProductIrontype())//
						.productIrontypeweight(mesproductVo.getProductIrontypeweight())//
						.productMaterialname(mesproductVo.getProductMaterialname())//
						.productImgid(mesproductVo.getProductImgid())//
						.productLuhao(mesproductVo.getProductLuhao())//
						.productMaterialsource(mesproductVo.getProductMaterialsource())//
						.productStatus(mesproductVo.getProductStatus())//
						.productRemark(mesproductVo.getProductRemark()).build();
				pd.setProductOperateIp("127.0.0.1");
				pd.setProductOperateTime(new Date());
				pd.setProductOperator("胡某某");
				MesProductMapper mapper = sqlSession.getMapper(MesProductMapper.class);
				// 批量增加
				mapper.insertSelective(pd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new SysMineException("创建过程问题");
		}
	}

	// 创建一个字符串
	public List<String> createProductIdsDefault(Long ocounts) {
		if (ig == null) {
			ig = new IdGenerator();
		}
		ig.setCurrentdbidscount(getProductCount());
		List<String> list = ig.initIds(ocounts);
		ig.clear();
		return list;
	}

	// 等到表中的数据条数
	public Long getProductCount() {
		return mesProductCustomerMapper.getProductCount();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// 1 默认生成代码
	// 2 手工生成代码
	// id生成器
	class IdGenerator {
		// 数量起始位置
		private Long currentdbidscount;
		private List<String> ids = new ArrayList<String>();
		private String idpre;
//		private String yearstr;
		private String idafter;

		public IdGenerator() {
		}

		public Long getCurrentdbidscount() {
			return currentdbidscount;
		}

		public void setCurrentdbidscount(Long currentdbidscount) {
			this.currentdbidscount = currentdbidscount;
			if (null == this.ids) {
				this.ids = new ArrayList<String>();
			}
		}

		public List<String> getIds() {
			return ids;
		}

		public void setIds(List<String> ids) {
			this.ids = ids;
		}

		public String getIdpre() {
			return idpre;
		}

		public void setIdpre(String idpre) {
			this.idpre = idpre;
		}

//		public String getYearstr() {
//			return yearstr;
//		}
//
//		public void setYearstr(String yearstr) {
//			this.yearstr = yearstr;
//		}

		public String getIdafter() {
			return idafter;
		}

		public void setIdafter(String idafter) {
			this.idafter = idafter;
		}

		public List<String> initIds(Long ocounts) {
			for (int i = 0; i < ocounts; i++) {
				this.ids.add(getIdPre() + getIdAfter(i));
			}
			return this.ids;
		}

		//
		private String getIdAfter(int addcount) {
			// 系统默认生成5位 ZX1700001
			int goallength = 6;
			// 获取数据库order的总数量+1+循环次数(addcount)
			int count = this.currentdbidscount.intValue() + 1 + addcount;
			StringBuilder sBuilder = new StringBuilder("");
			// 计算与5位数的差值
			int length = goallength - new String(count + "").length();
			for (int i = 0; i < length; i++) {
				sBuilder.append("0");
			}
			sBuilder.append(count + "");
			return sBuilder.toString();
		}

		private String getIdPre() {
			// idpre==null?this.idpre="ZX":this.idpre=idpre;
			this.idpre = "ZX_P_";
			return this.idpre;
		}

//		private String yearStr() {
//			Date currentdate = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			String yearstr = sdf.format(currentdate).substring(2, 4);
//			return yearstr;
//		}

		public void clear() {
			this.ids = null;
		}

		@Override
		public String toString() {
			return "IdGenerator [ids=" + ids + "]";
		}
	}

//	public PageResult<ProductDto> searchPageList(SearchProductParam param, PageQuery page) {
//		// TODO Auto-generated method stub
//		BeanValidator.check(page);
//		SearchProductDto dto=new SearchProductDto();
//		//判断关键词的模糊进行查询
//		if (StringUtils.isNotBlank(param.getKeyword())) {
//			dto.setKeyword("%" + param.getKeyword() + "%");
//		}
//		//
//		if (StringUtils.isNotBlank(param.getSearch_source())) {
//			dto.setSearch_source(param.getSearch_source());
//		}
//		//对状态进行判断
//		if (param.getSearch_status() != null) {
//			dto.setSearch_status(param.getSearch_status());
//		}
//		//
//		int count = mesProductCustomerMapper.countBySearchBindListDto(dto);
//
//		//System.out.println("/////////////////");
//		//System.out.println(count);
//	
//		if (count > 0) {
//			List<ProductDto> productList = mesProductCustomerMapper.getPageListBySearchBindListDto(dto, page);
//			for(ProductDto dt:productList) {
//				System.out.println(dt);
//			}
//			return PageResult.<ProductDto>builder().total(count).data(productList).build();
//		}
//
//		return PageResult.<ProductDto>builder().build();
//	}

	// 批量增加材料
//	public void insert(MesProductVo productVo) {
//		// 校验
//		BeanValidator.check(productVo);
//		// 获取增加个数
//		Integer counts = productVo.getCounts();
//		if (counts != null && counts > 0) {
//			for (int i = 0; i < counts; i++) {
//				// 批量增加-productDto
//				MesProduct pd = MesProduct.builder().productId(UUIDUtil.generateUUID())//
//						.productTargetweight(productVo.getProductTargetweight())//
//						.productRealweight(productVo.getProductRealweight())//
//						.productLeftweight(productVo.getProductLeftweight())//
//						.productBakweight(productVo.getProductLeftweight())//
//						.productIrontype(productVo.getProductIrontype())//
//						.productIrontypeweight(productVo.getProductIrontypeweight())//
//						.productMaterialname(productVo.getProductMaterialname())//
//						.productImgid(productVo.getProductImgid())//
//						.productLuhao(productVo.getProductLuhao())//
//						.productMaterialsource(productVo.getProductMaterialsource())//
//						.productStatus(productVo.getProductStatus())//
//						.productRemark(productVo.getProductRemark()).build();
//				pd.setProductOperateIp("127.0.0.1");
//				pd.setProductOperateTime(new Date());
//				pd.setProductOperator("胡某某");
//				MesProductMapper mapper = sqlSession.getMapper(MesProductMapper.class);
//				// 批量增加
//				mapper.insertSelective(pd);
//			}
//		}
//	}

	// 批量到库 分页
	public PageResult<ProductDto> searchPageList(SearchProductParam param, PageQuery page) {
		// 校验
		BeanValidator.check(page);
		// vo-dto
		SearchProductDto dto = new SearchProductDto();

		if (StringUtils.isNotBlank(param.getKeyword())) {
			dto.setKeyword("%" + param.getKeyword() + "%");
		}
		if (StringUtils.isNotBlank(param.getSearch_source())) {
			dto.setSearch_source(param.getSearch_source());
		}
		if (param.getSearch_status() != null) {
			dto.setSearch_status(param.getSearch_status());
		}
		int count = mesProductCustomerMapper.countBySearchDto(dto);
		if (count > 0) {
			List<ProductDto> productList = mesProductCustomerMapper.getPageListBySearchDto(dto, page);
			return PageResult.<ProductDto>builder().total(count).data(productList).build();
		}
		return PageResult.<ProductDto>builder().build();
	}

	// 到库查询修改
	public void update(MesProductVo productVo) {
		// 校验
		BeanValidator.check(productVo);
		
		MesProduct product=mesProdcutMapper.selectByPrimaryKey(productVo.getId());
		product.setProductImgid(productVo.getProductImgid());
		product.setProductLuhao(productVo.getProductLuhao());
		product.setProductIrontype(productVo.getProductIrontype());
		product.setProductIrontypeweight(productVo.getProductIrontypeweight());
		product.setProductMaterialname(productVo.getProductMaterialname());
		product.setProductTargetweight(productVo.getProductTargetweight());
		product.setProductMaterialsource(productVo.getProductMaterialsource());
		product.setProductRemark(productVo.getProductRemark());
		product.setProductRealweight(productVo.getProductRealweight());
		
		float temp=product.getProductLeftweight()-product.getProductBakweight();
		float leftweight=product.getProductLeftweight();
		
		product.setProductLeftweight(productVo.getProductLeftweight());
		//剩余重量备份需要重新设置
		product.setProductBakweight(product.getProductLeftweight()-temp);
		
		if(leftweight>=temp)
		mesProdcutMapper.updateByPrimaryKeySelective(product);
	}

	// 绑定钢材分页
	public PageResult<ProductDto> searchPageBindList(SearchProductParam param, PageQuery page) {
		// TODO Auto-generated method stub
		BeanValidator.check(page);
		SearchProductDto dto = new SearchProductDto();
		if (StringUtils.isNotBlank(param.getKeyword())) {
			dto.setKeyword("%" + param.getKeyword() + "%");
		}
		if (StringUtils.isNotBlank(param.getSearch_source())) {
			dto.setSearch_source(param.getSearch_source());
		}
		if (param.getSearch_status() != null) {
			dto.setSearch_status(param.getSearch_status());
		}
		int count = mesProductCustomerMapper.countBySearchBindListDto(dto);
		if (count > 0) {
			List<ProductDto> productList = mesProductCustomerMapper.getPageListBySearchBindListDto(dto, page);
			return PageResult.<ProductDto>builder().total(count).data(productList).build();
		}

		return PageResult.<ProductDto>builder().build();
	}
////////
	public MesProduct selectById(String id) {
		if (StringUtils.isNotBlank(id) && StringUtils.isNotEmpty(id)) {
			MesProduct product = mesProdcutMapper.selectByPrimaryKey(Integer.parseInt(id));
			if (null != product) {
				return product;
			}
		}
		throw new RuntimeException("没有这个材料");
	}
////////

	//钢锭绑定子材料分页
	public PageResult<ProductDto> searchPageChildBindList(SearchProductParam param, PageQuery page) {
		// TODO Auto-generated method stub
		BeanValidator.check(page);
		SearchProductDto dto=new SearchProductDto();
		if(StringUtils.isNotBlank(param.getSearch_source())) {
			dto.setSearch_source(param.getSearch_source());
		}
		int count=mesProductCustomerMapper.countBySearchChildBindListDto(dto);
		if(count>0) {
			List<ProductDto> productList=mesProductCustomerMapper.getPageListBySearchChildBindListDto(dto,page);
			return PageResult.<ProductDto>builder().total(count).data(productList).build();
		}
		
		return PageResult.<ProductDto>builder().build();
	}

	//钢材绑定钢锭逻辑（绑定逻辑）
	public void bind(String parentId, String childId) {
		// TODO Auto-generated method stub
		Integer pid=Integer.parseInt(parentId);
		Integer cid=Integer.parseInt(childId);
		
		MesProduct parent=mesProdcutMapper.selectByPrimaryKey(pid);
		MesProduct child=mesProdcutMapper.selectByPrimaryKey(cid);
		
		//钢材的理论剩余和真实剩余比钢锭的工艺重量大或等
		//后台的健壮性判断，防止页面js效果失效
		if(parent.getProductLeftweight()>=parent.getProductBakweight()&&parent.getProductBakweight()>=child.getProductTargetweight()) {
			//计算钢材的剩余理论重量剩余值
			parent.setProductBakweight(parent.getProductBakweight()-child.getProductTargetweight());
			//计算钢锭的理论重量剩余值，修改status为真，绑定pid
			child.setpId(pid);
			child.setProductBakweight(child.getProductTargetweight());
			child.setProductStatus(1);
			//更新parent与child  钢材 与 钢锭
			mesProdcutMapper.updateByPrimaryKeySelective(parent);
			mesProdcutMapper.updateByPrimaryKeySelective(child);
		}
	}

	//查看已绑定的钢锭列表
	public PageResult<ProductDto> searchPageParentBindList(SearchProductParam param, PageQuery page) {
		// TODO Auto-generated method stub
		BeanValidator.check(page);
		// vo-dto
		SearchProductDto dto = new SearchProductDto();
		if (StringUtils.isNotBlank(param.getSearch_source())) {
			dto.setSearch_source(param.getSearch_source());
		}
		if(param.getPid()!=null) {
			dto.setPid(param.getPid());
		}
		int count = mesProductCustomerMapper.countBySearchParentBindListDto(dto);

		if (count > 0) {
			List<ProductDto> productList = mesProductCustomerMapper.getPageListBySearchParentBindListDto(dto, page);
			return PageResult.<ProductDto>builder().total(count).data(productList).build();
		}

		return PageResult.<ProductDto>builder().build();
	}

	//钢材解绑钢锭
	public boolean unbound(String childId) {
		if(StringUtils.isNoneBlank(childId)&&StringUtils.isNotEmpty(childId)) {
			Integer cid=Integer.parseInt(childId);
			MesProduct product=mesProdcutMapper.selectByPrimaryKey(cid);
			//解绑过程需要查看投料重量是否为空，如果为空则可以解绑
			if(product.getProductRealweight()!=null&&product.getProductRealweight()>0) {
				return false;
			}
			//status-0
			product.setProductStatus(0);
			//backweight-0
			product.setProductBakweight(0f);
			//pid-null
			Integer pid=product.getpId();
			product.setpId(null);
			MesProduct parent=mesProdcutMapper.selectByPrimaryKey(pid);
			parent.setProductBakweight(parent.getProductBakweight()+product.getProductTargetweight());
			//执行绑定双方修改
			mesProdcutMapper.updateByPrimaryKey(product);
			mesProdcutMapper.updateByPrimaryKeySelective(parent);
		}
		return false;
	}
	

}
