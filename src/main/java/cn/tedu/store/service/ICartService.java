package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ex.IllegalParamException;

public interface ICartService {

	/**
	 * 添加到购物车
	 * @param goodsId 商品的id
	 * @param uid 当前登录的用户id
	 * @param goodsNum 商品数量
	 * @param IllegalParamException 不允许将购物车中的商品数量修改为小于1的值否则抛异常
	 */
	void addToCart(Long goodsId, Integer uid,Integer goodsNum) throws IllegalParamException;

	/**
	 * 根据用户id和商品id获取购物车信息
	 * @param uid 用户id
	 * @param goodsId 商品id 
	 * @return 返回商品信息
	 */
	Cart getCartByUidAndGoodsId(Integer uid, Long goodsId);
	
	/**
	 * 更新商品数量
	 * @param id 购物车id
	 * @param goodsNum 商品数量
	 * @return 返回受影响行数
	 */
	Integer changeGoodsNum(@Param("id") Integer id, @Param("goodsNum") Integer goodsNum);
	
	/**
	 * 获取某用户的购物车数据列表
	 * @param uid 用户id
	 * @return 购物车数据列表
	 */
	List<Cart> getListByUid(Integer uid);

}
