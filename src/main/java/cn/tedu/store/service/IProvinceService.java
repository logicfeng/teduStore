package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Province;

public interface IProvinceService {
	
	/**
	 * 查询省列表
	 * @return 返回省列表
	 */
	List<Province> getProvinceList();

	/**
	 * 根据省邮编返回省
	 * @param provinceCode
	 * @return
	 */
	Province getProvinceByCode(String provinceCode);

}
