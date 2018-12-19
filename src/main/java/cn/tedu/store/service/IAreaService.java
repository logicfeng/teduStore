package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Area;

public interface IAreaService {
	/**
	 * 根据城市编码查询地区列表
	 * @param CityCode 城市编码
	 * @return 返回地区列表
	 */
	List<Area> getAreaListByCityCode(String cityCode);

	/**
	 * 根据地区编号查询具体的地区名
	 * @param areaCode 地区编号
	 * @return 返回地区名
	 */
	Area getAreaByCode(String areaCode);

}
