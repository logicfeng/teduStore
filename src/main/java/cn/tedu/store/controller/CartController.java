package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.ServiceException;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {
	
	@Autowired
	private ICartService cartService;
	
	@RequestMapping("/list.do")
	public String showList(ModelMap modelMap,
			HttpSession session) {
		// 获取当前登录的用户的id
		Integer uid = getUidFromSession(session);
		
		// 获取当前用户的购物车数据
		List<Cart> carts
			= cartService.getListByUid(uid);
		
		// 封装数据：当前用户的购物车数据
		modelMap.addAttribute("carts", carts);
		
		// 执行转发
		return "cart_list";
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public ResponseResult<Void> handleAddToCart(@RequestParam("goods_id")Long goodsId,
			@RequestParam("num")Integer goodsNum,HttpSession session){
		ResponseResult<Void> rr;
		//获取uid
		Integer uid = getUidFromSession(session);
		//调用方法
		System.out.println("uid="+uid);
		try {
			cartService.addToCart(goodsId,uid,goodsNum);
			rr = new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (ServiceException e) {
			rr = new ResponseResult<Void>(e);
		}
		return rr;
	}
	
}
