package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.service.IGoodsService;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController {

	@Autowired
	private IGoodsCategoryService goodsCategoryService;
	
	@Autowired
	private IGoodsService goodsService;
	
	@RequestMapping("/index.do")
	public String showIndex(ModelMap modelMap) {
		//==========声明相关变量===============
		Long[] parentIds = {162l,171l,186l};
		//热销电脑参数
		Long hotGoodCategoryId = 163l;
		Integer hotGoodsCount =3;
		//结果
		List<List<GoodsCategory>> goodsCategoiesList = new ArrayList<List<GoodsCategory>>();
		//调函数
		for(int i=0;i<parentIds.length;i++) {
			goodsCategoiesList.add(goodsCategoryService.getListByParentId(parentIds[i]));  
		}
		List<Goods> goodList = goodsService.getHotGoods(hotGoodCategoryId, hotGoodsCount);
		modelMap.addAttribute("goodsCategoiesList",goodsCategoiesList);
		modelMap.addAttribute("goodList",goodList);
		return "index";
	}
}
