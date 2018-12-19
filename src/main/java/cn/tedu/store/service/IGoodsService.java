package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Goods;

public interface IGoodsService {
	
	/**
	 * 每页多少条
	 */
	int COUNT_PRE_PAGE = 20;
	
	/**
	 * 获取热销产品列表
	 * @return 返回热销产品列表
	 */
	List<Goods> getHotGoods(Long categoryId,Integer count);

	/**
	 * 根据商品id获取商品列表
	 * @param categoryId 商品分类id
	 * @param page 获取第几页的数据
	 * @return  返回商品的列表
	 * @see #COUNT_PRE_PAGE
	 */
	List<Goods> getListByCategoryId(Long categoryId,Integer page);
	
	/**
	 * 获取某个分类下的商品的数量
	 * @param categoryId 商品分类id
	 * @return 返回该商品的数量
	 */
	Integer getCountByCategoryId(Long categoryId);
	
	/**
	 * 根据Id获取商品信息
	 * @param id 商品Id
	 * @return 返回商品的信息
	 */
	Goods getGoodsById(Long id);
	
}
