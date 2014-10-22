package onekr.test;
//package com.bgoal.test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.bgoal.mall.api.service.identity.UserService;
//import com.bgoal.mall.api.service.standard.AreaService;
//import com.bgoal.mall.api.service.standard.dto.SupplierDto;
//import com.bgoal.mall.api.service.trading.CatalogService;
//import com.bgoal.mall.api.service.trading.ProductService;
//import com.bgoal.mall.api.service.trading.SupplierService;
//import com.bgoal.mall.api.service.trading.dto.CatalogDto;
//import com.bgoal.mall.api.service.trading.dto.DishDto;
//import com.bgoal.mall.core.enums.Status;
//import com.bgoal.mall.core.enums.UserGroup;
//import com.bgoal.mall.core.model.User;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath*:/applicationContext-test.xml")
//@ActiveProfiles("test")
//public class MainTest {
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private SupplierService supplierService;
//	@Autowired
//	private CatalogService catalogService;
//	@Autowired
//	private AreaService areaService;
//	@Autowired
//	private ProductService dishService;
//	
//	@Test
//	public void createUser() {
//		User user = new User();
//		String name = "micwing";
//		user.setNickName(name);
//		user.setUserName(name);
//		user.setEmail(name+"@163.com");
//		userService.addUser(user, "123456", UserGroup.USER);
//	}
//	
//	@Test
//	public void createSupplier() {
//		SupplierDto dto = new SupplierDto();
//		dto.setAreaCode(110000L);
//		dto.setUser(userService.findUser(1L));
//		dto.setName("迈克的外卖店");
//		dto.setTel("18655588888");
//		dto.setTemplateName("orange");
//		dto.setAround("雨花台区雨花体育中心周边2公里范围");
//		dto.setBusinessTime("中午10点到1点，下午4点到7点");
//		dto.setNotice("小店新开张全场餐点8折优惠");
//		dto.setStartPrice(10.0);
//		supplierService.saveSupplier(dto);
//	}
//	
//	@Test
//	public void createCatalog() {
//		CatalogDto dto = new CatalogDto();
//		dto.setName("中餐");
//		dto.setRank(1);
//		dto.setSupplier(supplierService.findSupplier(1L));
//		catalogService.save(dto);
//		
//		CatalogDto dto2 = new CatalogDto();
//		dto2.setName("晚餐");
//		dto2.setRank(2);
//		dto2.setSupplier(supplierService.findSupplier(1L));
//		catalogService.save(dto2);
//	}
//	
//	@Test
//	public void createDish() {
//		for (int i = 0; i < 20; i++) {
//			DishDto dto = new DishDto();
//			dto.setCatalog(catalogService.findById(1));
//			dto.setStatus(Status.NORMAL);
//			dto.setSupplier(supplierService.findSupplier(1L));
//			dto.setPrice(15D);
//			dto.setTitle("韩式拌饭");
//			dto.setSubtitle("微辣");
//			dishService.saveDish(dto);
//		}
//		for (int i = 0; i < 20; i++) {
//			DishDto dto = new DishDto();
//			dto.setCatalog(catalogService.findById(2));
//			dto.setStatus(Status.NORMAL);
//			dto.setSupplier(supplierService.findSupplier(1L));
//			dto.setPrice(15D);
//			dto.setTitle("10元盒饭");
//			dto.setSubtitle("1大荤+1小荤+1素菜+1煎蛋+米饭");
//			dishService.saveDish(dto);
//		}
//	}
//	
//}
