package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConfilictException;
import cn.tedu.store.util.Validator;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	@Autowired
	private IUserService userService;

	/**
	 * 上传的头像的最大尺寸，单位kb；
	 */
	public static final long MAX_AVATOR_SIZE = 50000;
	
	@RequestMapping("/login.do")
	public String showLogin() {
		return "user_login";
	}

	@RequestMapping("/reg.do")
	public String showReg() {
		return "user_reg";
	}

	@RequestMapping("/change_password.do")
	public String showChangePassword() {
		return "user_change_password";
	}
	
	@RequestMapping("/change_info.do")
	public String showChangeInfo(HttpSession session,ModelMap modelMap) {
		//获取当前登录的用户的id
		Integer uid = getUidFromSession(session);
		//根据用户id查询用户
		User user = userService.findUserById(uid);
		//将用户数据封装，以准备转发
		modelMap.addAttribute("user",user);
		return "user_change_info";
	}
	
	@RequestMapping(value="/handle_change_info.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> handleChangeInfo(String username,Integer gender,
			String phone,String email,HttpSession session,
			MultipartFile avator,MultipartHttpServletRequest request){
		//用户上传的头像在服务器端的路径
		String avatorPath = null;
		ResponseResult<String> rr;
		//从session中获取uid
		Integer uid = getUidFromSession(session);
		//上传头像
		//判断用户是否选择上传了新的头像
		if(!avator.isEmpty()) {
		//验证文件类型
		String contentType = avator.getContentType();
		if(!"image/png".equals(contentType)&&!"image/jpeg".equals(contentType)&&!"image/bmp".equals(contentType)) {
			//png jpg gif bmp
			rr = new ResponseResult<String>(ResponseResult.STATE_OK,"不支持上传"+contentType+"类型的文件！");
			return rr;
		}
		//验证文件大小
		long size = avator.getSize();
		if(size>MAX_AVATOR_SIZE*1024) {
			rr = new ResponseResult<String>(ResponseResult.STATE_OK,"上传的头像文件不允许超过"+MAX_AVATOR_SIZE+"kb！");
			return rr;
		}
		//所有用户头像都在这个文件夹
		String avatorDirPath = request.getServletContext().getRealPath("/upload/image");
		File avatorDir = new File(avatorDirPath);
//		if(!avatorDir.exists()) {
//			avatorDir.mkdirs();
//		}
		//头像文件名，每个头像文件名都应该不相同
//		String filename = "";//201810094511.png
		//
		Date date = new Date();
		//获取扩展名
		String originalFilename = avator.getOriginalFilename();
		String suffix =originalFilename.substring(originalFilename.lastIndexOf("."));
		String filename = getDateString(date)+uid+suffix;
		File dest = new File(avatorDir,filename);
		//保存数据
		try {
			avator.transferTo(dest);
			//确定用户头像在服务器端的路径
			avatorPath = "upload/image/"+filename;
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
		//执行修改个人资料
		System.out.println("username"+username);
		try {
			userService.changeInfo(uid,avatorPath, username, gender, phone, email);
			rr = new ResponseResult<String>(ResponseResult.STATE_OK);
			rr.setDate(avatorPath);
		} catch (UserNotFoundException e) {
			rr = new ResponseResult<String>(e);
		} catch (UsernameConfilictException e) {
			rr = new ResponseResult<String>(e);
		}
		return rr;
	}
	
	@RequestMapping(value="/handle_change_password.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleChangePassword(@RequestParam("old_password")String oldPassword,
			@RequestParam("new_password")String newPassword,HttpSession session){
		//验证数据的有效性
		ResponseResult<Void> rr;
//		Integer uid = (Integer)session.getAttribute("uid");
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		try {
			userService.changePassword(uid, oldPassword, newPassword);
			rr = new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (UserNotFoundException e) {
			rr = new ResponseResult<Void>(-1,e.getMessage());
		} catch (PasswordNotMatchException e) {
			rr = new ResponseResult<Void>(-2,e.getMessage());
		}
		return rr;
	}
	
	@RequestMapping(value = "/handle_login.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleLogin(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		// 判断数据的有效性
		ResponseResult<Void> rr;
		boolean result = Validator.checkUsername(username);
		if (!result) {
			rr = new ResponseResult<Void>(ResponseResult.STATE_ERR, "你输入的用户名不合法！");
			return rr;
		}
		try {
			User user = userService.login(username, password);
			session.setAttribute("uid", user.getId());
			session.setAttribute("username",user.getUsername());
			rr = new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (UserNotFoundException e) {
			rr = new ResponseResult<Void>(-1,e.getMessage());
		} catch (PasswordNotMatchException e) {
			rr = new ResponseResult<Void>(-2,e.getMessage());
		}
		return rr;
	}

	@RequestMapping(value = "/handle_reg.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> hanleReg(@Param("username") String username, @Param("password") String password,
			String email, String phone, Integer gender) {
		ResponseResult<Void> rr;
		// 忽略数据的有效性
		boolean result = Validator.checkUsername(username);
		if (!result) {
			rr = new ResponseResult<Void>(ResponseResult.STATE_ERR, "你输入的用户名有误");
			return rr;
		}
		try {
			// 将参数封装到User里面
			User user = new User(username, password, gender, phone, email);
			userService.reg(user);
			// 封装返回结果
			rr = new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (UsernameConfilictException e) {
			rr = new ResponseResult<Void>(e);
		}
		return rr;
	}
	
	private final String pattern = "yyyyMMddHHmmss";
	private final SimpleDateFormat sdf = new SimpleDateFormat(pattern,Locale.CHINA);
	private String getDateString(Date date) {
		return sdf.format(date);
	}

}
