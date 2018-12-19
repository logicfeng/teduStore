package cn.tedu.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ex.IllegalParamException;
import cn.tedu.store.service.ex.InsertDataException;

@Service("cartService")
public class CartServiceImpl implements ICartService {

	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private IGoodsService goodsService;
	
	public void addToCart(Long goodsId, Integer uid,Integer goodsNum) {
		//添加到购物车可能时insert也可能时update
		//查询数据表中是否存在该商品
		Cart cart = getCartByUidAndGoodsId(uid, goodsId);
		if(cart==null) {
			// 判断当前商品有没有添加到购物车
			insert(goodsId,uid,goodsNum);
		}else {
			//TODO 已添加：执行update
			//获取购物车id
			Integer id = cart.getId();
			//获取购物车中原本数量
			Integer n = cart.getGoodsNum();
			//计算新数量
			Integer newGoodsNum = n + goodsNum;
			//判断新数量是否大于0
			if(newGoodsNum>0) {
				changeGoodsNum(id, newGoodsNum);
			}else {
				throw new IllegalParamException("不允许将购物车中的商品数量修改为小于1的值！"+goodsNum); 
			}
		}
		//未添加：执行insert
	}
	
	public void insert(Long goodsId, Integer uid,Integer goodsNum) {
		Goods goods = goodsService.getGoodsById(goodsId);
		Cart cart = new Cart();
		cart.setUid(uid);
		cart.setGoodsId(goodsId);
		cart.setGoodsImage(goods.getImage());
		cart.setGoodsPrice(goods.getPrice());
		cart.setGoodsTitle(goods.getTitle());
		cart.setGoodsNum(goodsNum);
		Integer rows = cartMapper.insert(cart);
		if(rows!=1) {
			throw new InsertDataException("添加购物车信息失败"+cart);
		}
	}

	/**
	 * 根据用户id和商品id获取购物车信息
	 */
	public Cart getCartByUidAndGoodsId(Integer uid, Long goodsId) {
		return cartMapper.getCartByUidAndGoodsId(uid, goodsId);
	}

	/**
	 * 修改商品数量
	 */
	public Integer changeGoodsNum(Integer id, Integer goodsNum) {
		return cartMapper.changeGoodsNum(id, goodsNum);
	}

	public List<Cart> getListByUid(Integer uid) {
		return cartMapper.getListByUid(uid);
	}

}
