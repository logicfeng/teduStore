package cn.tedu.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.mapper.GoodsCategoryMapper;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.service.ex.ServiceException;

public class AddressMapperTest {
	
	@Test
	public void getListByCategorId() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		CartMapper cartMapper = ac.getBean("cartMapper",CartMapper.class);
		Cart cart = new Cart();
		cart.setUid(11);
		cart.setGoodsId(10000017l);
		cart.setGoodsNum(1);
		Integer list = cartMapper.insert(cart);
		System.out.println(list);
		ac.close();
	}
	
	@Test
	public void getListByCategoryId() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		GoodsMapper goodsMapper = ac.getBean("goodsMapper",GoodsMapper.class);
		List<Goods> list = goodsMapper.getListByCategoryId(163l, 3, 3);
		System.out.println(list);
		ac.close();
	}
	
	@Test
	public void getListByParentId() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		GoodsCategoryMapper goodsCategoryMapper = ac.getBean("goodsCategoryMapper",GoodsCategoryMapper.class);
		List<GoodsCategory> list = goodsCategoryMapper.getListByParentId(162l);
		System.out.println(list);
		ac.close();
	}
	
	@Test
	public void testSetDefult() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IGoodsCategoryService goodsCategoryService = ac.getBean("goodsCategoryService",IGoodsCategoryService.class);
		try {
			List<GoodsCategory> result = goodsCategoryService.getListByParentId(1611l);
			System.out.println(result);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testSetDefault() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		AddressMapper addressMapper = ac.getBean("addressMapper",AddressMapper.class);
		 addressMapper.setDefault(12, null, "0");
		System.out.println("修改完成！");
		ac.close();
	}
	
	@Test
	public void testChangeInf() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IAddressService addressService = ac.getBean("addressService",IAddressService.class);
		try {
			Address address = new Address();
			address.setRecvAddress("由圣杯路");
			address.setUid(2);
			address.setRecvName("胡云峰");
			Address result = addressService.add("胡云峰", address);
			System.out.println(result);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGetAddress() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		AddressMapper addressMapper = ac.getBean("addressMapper",AddressMapper.class);
		List<Address> rows = addressMapper.getAddressList(11);
		System.out.println(rows);
		ac.close();
	}
	
	@Test
	public void testInsertAddress() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		AddressMapper addressMapper = ac.getBean("addressMapper",AddressMapper.class);
		Integer rows = addressMapper.getAddressCountByUid(16);
		System.out.println(rows);
		ac.close();
	}

}
