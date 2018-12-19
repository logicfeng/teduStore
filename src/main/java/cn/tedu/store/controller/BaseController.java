package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

/**
 * 项目中所有控制器类的基类
 * @author chengheng
 *
 */
public abstract class BaseController {
	
	/**
	 * 从session中获取uid
	 * @param session HttpSession的对象
	 * @return 用户的uid
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
}
