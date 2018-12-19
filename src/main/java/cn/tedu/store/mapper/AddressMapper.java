package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;

public interface AddressMapper {
	
	/**
	 * 新增收货地址
	 * @param address 收货地址数据
	 * @return 返回受影响的行数
	 */
	Integer insert(Address address);
	
	/**
	 * 根据用户uid获取地址信息列表
	 * @param uid 用户uid
	 * @return 返回用户的地址信息列表
	 */
	List<Address> getAddressList(Integer uid);

	/**
	 * 根据用户uid查询所拥有的地址数量
	 * @param uid 用户的uid
	 * @return 返回用户所拥有的地址数量
	 */
	Integer getAddressCountByUid(Integer uid);
	
	/**
	 * 根据用户id，地址id，修改用户的默认收货地址
	 * @param uid 用户uid
	 * @param id  地址id
	 * @return 返回受影响的行数
	 */
	Integer setDefault(@Param("uid")Integer uid,@Param("id")Integer id,@Param("isDefault")String isDefault);
}
