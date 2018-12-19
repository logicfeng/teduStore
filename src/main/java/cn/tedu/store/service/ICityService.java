package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.City;

public interface ICityService {
	/**
	 * 根据省编码获取城市列表
	 * @param provinceCode 省编码
	 * @return 返回城市列表
	 */
	List<City> getCityListByProvinceCode(String provinceCode);

	/**
	 * 根据城市编码获取城市
	 * @param cityCode 城市编码
	 * @return 返回城市具体名
	 */
	City getCityByCode(String cityCode);

}
