package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.GoodsCategory;

public interface GoodsCategoryMapper {
	
	/**
	 * 根据上级id查询商品列表
	 * @param parentId 上级id 
	 * @return 返回商品的list集合
	 */
	List<GoodsCategory> getListByParentId(Long parentId);

}
