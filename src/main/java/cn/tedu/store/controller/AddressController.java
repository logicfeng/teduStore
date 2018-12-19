package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Province;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IProvinceService;
import cn.tedu.store.service.ex.DataNotFoundException;
import cn.tedu.store.service.ex.InsertDataException;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController {

	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private IProvinceService provinceService;
	
	@RequestMapping("/list.do")
	public String showList(ModelMap modelMap,HttpSession session) {
		List<Province> provinces = provinceService.getProvinceList();
		modelMap.addAttribute("provinces",provinces);
		Integer uid = getUidFromSession(session);
		List<Address> addresses = addressService.getAddressList(uid);
		modelMap.addAttribute("addresses",addresses);
		return "address_list";
	}
	
	@RequestMapping("/set_default.do")
	public String handleSetDefault(@Param("id")Integer id,HttpSession session,ModelMap modelMap) {
		Integer uid = getUidFromSession(session);
		//调用setDefault方法
		try {
			addressService.setDefault(uid,id);
			return "redirect:list.do";
		} catch (DataNotFoundException e) {
			modelMap.addAttribute("message",e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value="/handle_add.do",method=RequestMethod.POST)
	public String handleAdd(HttpSession session,Address address,ModelMap modelMap) {
		//检查数据有效性
		Integer uid = getUidFromSession(session);
		address.setUid(uid);
		String username=session.getAttribute("username").toString();
		try {
			addressService.add(username, address);
			return "redirect:list.do";
		} catch (InsertDataException e) {
			//封装错误信息
			modelMap.addAttribute("message",e.getMessage());
			//转发错误页面
			return "error";
		}
		
	}
}







