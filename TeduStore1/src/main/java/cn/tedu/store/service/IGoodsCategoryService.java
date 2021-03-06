package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.GoodsCategory;

public interface IGoodsCategoryService {
	
	/**
	 * 根据商品分类的父级id，获取商品分类的列表
	 * @param parentId 商品分类的父级id
	 * @return 商品分类的列表
	 */
	List<GoodsCategory> getListByParentId(Long parentId);
}
