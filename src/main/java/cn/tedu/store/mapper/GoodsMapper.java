package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;

public interface GoodsMapper {
	
	/**
	 * 获取热销产品列表
	 * @return 返回热销产品列表
	 */
	List<Goods> getHotGoods(@Param("categoryId")Long categoryId,@Param("count")Integer count);

	/**
	 * 根据商品id获取商品列表
	 * @param categoryId 商品分类id
	 * @param offset 获取数据时的偏移量
	 * @param count 查询商品的数量
	 * @return  返回商品的列表
	 */
	List<Goods> getListByCategoryId(@Param("categoryId")Long categoryId,@Param("offset")Integer offset,
			@Param("count")Integer count);
	
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
