package cn.tedu.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Province;
import cn.tedu.store.mapper.ProvinceMapper;

public class ProvinceMapperTest {
	
	@Test
	public void testInsertAddress() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		ProvinceMapper provinceMapper = ac.getBean("provinceMapper",ProvinceMapper.class);
		List<Province> rows = provinceMapper.getProvinceList();
		System.out.println(rows);
		ac.close();
	}

}
