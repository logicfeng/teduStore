package cn.tedu.store.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.AreaMapper;
import cn.tedu.store.mapper.CityMapper;
import cn.tedu.store.mapper.ProvinceMapper;
import cn.tedu.store.service.ex.DataNotFoundException;
import cn.tedu.store.service.ex.InsertDataException;

@Service("addressService")
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressMapper addressMapper;
	
	
	@Autowired
	private IProvinceService provinceService;
	
	
	@Autowired
	private ICityService cityService;
	
	
	@Autowired
	private IAreaService areaService;
	
	public Address add(String username, Address address) {
		//检查address中的uid和recvName是否为null
		// TODO 判断是否是第一个是的话默认，不是的话不默认
		Integer uid = address.getUid();
		if(getAddressCountByUid(uid)==0) {
			address.setIsDefault(1+"");
		}else {
			address.setIsDefault(0+"");
		}
//		address.setIsDefault(?);
		//封装省市区的中文名，即recvDistrict
		String recvDistrict = getRecvDistrict(
				address.getRecvProvince(),
				address.getRecvCity(),
				address.getRecvArea()
				);
		address.setRecvDistrict(recvDistrict);
		//封装日志信息
		Date now = new Date();
		address.setCreatedUser(username);
		address.setModifiedUser(username);
		address.setModifiedTime(now);
		address.setCreatedUser(username);
		address.setCreatedTime(now);
		//执行增加
		Integer rows = addressMapper.insert(address);
		//判断持久层操作的返回值
		if(rows==1) {
			//成功
			return address;
		}else {
			//失败抛出异常
			throw new InsertDataException("增加收货地址失败！："+address);
		}
	}
	
	/**
	 * 获取district
	 * @param provinceCode 省的编码
	 * @param cityCode 城市的编码
	 * @param areaCode 地区的编码
	 * @return
	 */
	private String getRecvDistrict(String provinceCode, String cityCode, String areaCode) {
		return provinceService.getProvinceByCode(provinceCode).getProvinceName()+","+cityService.
				getCityByCode(cityCode).getCityName()+","+areaService.getAreaByCode(areaCode).getAreaName();
	}

	/**
	 * 根据用户uid获取地址信息列表
	 */
	public List<Address> getAddressList(Integer uid) {
		return addressMapper.getAddressList(uid);
	}

	public Integer getAddressCountByUid(Integer uid) {
		return addressMapper.getAddressCountByUid(uid);
	}

	@Transactional
	public Integer setDefault(Integer uid, Integer id) {
		//先将该用户的所有收货地址设置为非默认
		addressMapper.setDefault(uid, null, "0");
		//再将指定的收货地址设置为默认
		Integer rows = addressMapper.setDefault(uid, id, "1");
		if(rows==1) {
			return 1;
		}else {
			throw new DataNotFoundException("尝试将id="+id+"数据设置为默认收货地址，操作失败！");
		}
	}

}






