package cn.tedu.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IGoodsService;
import cn.tedu.store.service.ex.ServiceException;

public class GoodsMapperTest {
	
	@Test
	public void testgetListByCategoryId() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		AddressMapper addressMapper = ac.getBean("addressMapper",AddressMapper.class);
		 addressMapper.setDefault(12, null, "0");
		System.out.println("修改完成！");
		ac.close();
	}
	
	@Test
	public void testSetDefult() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IGoodsService goodsService = ac.getBean("goodsService",IGoodsService.class);
		try {
			List<Goods> result = goodsService.getHotGoods(163l, 3);
			for(Goods good:result) {
				System.out.println(good);
			}
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
		ac.close();
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
		ac.close();
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
