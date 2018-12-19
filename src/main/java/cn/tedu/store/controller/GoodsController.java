package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.service.IGoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	private IGoodsService goodsService;
	
	@RequestMapping("/list.do")
	public String showGoodsList(@RequestParam("categoryId") Long categoryId,Integer page,ModelMap modelMap) {
		List<Goods> goodsList = goodsService.getListByCategoryId(categoryId, page);
		Integer goodsCount = goodsService.getCountByCategoryId(categoryId);
		int maxPage = (int) Math.ceil(1.0*goodsCount/IGoodsService.COUNT_PRE_PAGE);
		modelMap.addAttribute("categoryId",categoryId);
		modelMap.addAttribute("goodsList",goodsList);
		//封装数据，最大页码
		modelMap.addAttribute("maxPage",maxPage);
		return "goods_list";
	}
	
	@RequestMapping("/details.do")
	public String showDetails(@RequestParam("id")Long id,ModelMap modleMap) {
		//获取商品数据
		Goods goods = goodsService.getGoodsById(id);
		//封装
		modleMap.addAttribute("goods",goods);
		//执行转发
		return "goods_details";
	}
	

}








