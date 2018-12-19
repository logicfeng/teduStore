package cn.tedu.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Province;
import cn.tedu.store.mapper.ProvinceMapper;

@Service("provinceService")
public class ProvinceServiceImpl implements IProvinceService{

	@Autowired
	private ProvinceMapper provinceMapper;
	
	/**
	 * 查询省列表
	 */
	public List<Province> getProvinceList() {
		return provinceMapper.getProvinceList();
	}

	/**
	 * 根据省邮编返回省
	 */
	public Province getProvinceByCode(String provinceCode) {
		return provinceMapper.getProvinceByCode(provinceCode);
	}

}
