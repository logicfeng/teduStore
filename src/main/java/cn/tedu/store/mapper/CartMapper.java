package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;

public interface CartMapper {

	/**
	 * 添加到购物车表
	 * @param cart 购物车信息
	 * @return 返回受影响的行数
	 */
	Integer insert(Cart cart);
	
	/**
	 * 根据用户id和商品id获取购物车信息
	 * @param uid 用户id
	 * @param goodsId 商品id 
	 * @return 返回购物车信息
	 */
	Cart getCartByUidAndGoodsId(@Param("uid")Integer uid,@Param("goodsId") Long goodsId);

	/**
	 * 更新商品数量
	 * @param id 购物车id
	 * @param goodsNum 商品数量
	 * @return 返回受影响行数
	 */
	Integer changeGoodsNum(@Param("id") Integer id, @Param("goodsNum") Integer goodsNum);
	
	/**
	 * 获取某用户的购物车数据列表
	 * 
	 * @param uid
	 *            用户id
	 * @return 购物车数据列表
	 */
	List<Cart> getListByUid(Integer uid);
}
