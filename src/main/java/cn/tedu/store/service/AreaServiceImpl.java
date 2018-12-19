package cn.tedu.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Area;
import cn.tedu.store.mapper.AreaMapper;

@Service("areaService")
public class AreaServiceImpl implements IAreaService {

	@Autowired
	private AreaMapper areaMapper;
	
	/**
	 * 根据城市编码查询地区列表
	 */
	public List<Area> getAreaListByCityCode(String cityCode) {
		return areaMapper.getAreaListByCityCode(cityCode);
	}

	/**
	 * 根据地区编号查询具体的地区名
	 */
	public Area getAreaByCode(String areaCode) {
		return areaMapper.getAreaByCode(areaCode);
	}

}
