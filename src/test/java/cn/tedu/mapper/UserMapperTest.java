package cn.tedu.mapper;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.ServiceException;

public class UserMapperTest {
	
	@Test
	public void testInsertAddress() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		AddressMapper addressMapper = ac.getBean("addressMapper",AddressMapper.class);
		Address address = new Address();
		address.setUid(1);
		address.setRecvName("huyunfeng");
		address.setRecvProvince("河南");
		address.setRecvCity("郑州");
		address.setRecvArea("金水");
		address.setRecvAddress("优胜北路");
		address.setRecvPhone("15716687248");
		address.setRecvTag("公司");
		address.setRecvZip("110000");
		Integer result = addressMapper.insert(address);
		System.out.println(result);
	}
	
	@Test
	public void testChangeInf() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService userService = ac.getBean("userService",IUserService.class);
		try {
//			Integer result = userService.changeInfo(12,"barserker", 1, "15976654851", "asadf@sdf");
//			System.out.println(result);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testChangeInfo() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		User user = new User();
		user.setId(13);
		user.setUsername("dun");
		user.setGender(1);
		user.setPhone("15716687248");
		user.setEmail("ajdj@shd");
		user.setModifiedUser(user.getUsername());
		user.setModifiedTime(new Date());
		Integer result = userMapper.changeInfo(user);
		System.out.println(result);
	}
	
	@Test
	public void testchangePassword() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService userService = ac.getBean("userService",IUserService.class);
		try {
			Integer result = userService.changePassword(13, "456456456", "123123123");
			System.out.println(result);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testChangePassword() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		Integer result = userMapper.changePassword(1, "999", null, new Date());
		System.out.println(result);
	}
	
	@Test
	public void testFindUserById() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		User result = userMapper.findUserById(1);
		System.out.println(result);
	}
	
	@Test
	public void testLogin() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService userService = ac.getBean("userService",IUserService.class);
		try {
			User result = userService.login("lin", "123");
			System.out.println(result);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testReg() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService userService = ac.getBean("userService",IUserService.class);
		User user = new User();
		user.setUsername("lin");
		user.setPassword("123");
		user.setEmail("adsf@asd");
		user.setPhone("12716687248");
		try {
			User result = userService.reg(user);
			System.out.println(result);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testInsert() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		User user = new User();
		user.setUsername("huyunffeng");
		user.setPassword("123");
		user.setEmail("adsf@asd");
		user.setPhone("12716687248");
		Integer result = userMapper.insert(user);
		System.out.println(result);
	}

	@Test
	public void testFindUserByUsername() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		User result = userMapper.findUserByUsername("huyunffeng");
		System.out.println(result);
	}

}
