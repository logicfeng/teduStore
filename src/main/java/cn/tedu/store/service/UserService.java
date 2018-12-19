package cn.tedu.store.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConfilictException;
import cn.tedu.store.util.Validator;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 修改个人信息
	 */
	public Integer changeInfo(Integer uid,String avator, String username, Integer gender, String phone, String email) {
		//检验数据的有效性
				//验证用户名不符合要求则赋值为null
		if(!Validator.checkUsername(username)) {
			username=null;
		}
				//验证用户名不符合要求则赋值为null
//		if(!Validator.checkPhone(phone)) {
//			phone=null;
//		}
		//如果修改用户名，则用户名不可是其他用户已经注册
				//如果username符合要求
		if(username!=null) {
			User user = findUserByUsername(username);
			//如果用户名不重复
			if(user==null) {
				//则继续下一步操作
			}else {
				//如果用户名重复则判断是否是本人用户名
				//是本人的话则username赋值为null不修改
				if(user.getId().equals(uid)) {
					username=null;
				}else {
					throw new UsernameConfilictException("用户"+user.getUsername()+"已经存在！");
				}
			}
		}
		//将数据封装到User对象中
		User user = new User(uid, username, gender, phone, email, username, new Date());
		user.setAvator(avator);
		//封装日志数据
		
		
		Integer result = userMapper.changeInfo(user);
		if(result==1) {
			return result;
		}else {
			throw new UserNotFoundException("用户"+username+"没找到！");
		}
	}
	
	/**
	 * 修改用户密码
	 */
	public Integer changePassword(Integer id, String oldPassword, String newPassword) {
		//根据id查询用户信息
		User user = findUserById(id);
		if(user==null) {
			throw new UserNotFoundException("尝试操作的用户数据不存在！");
		}
		//获取uuid
		String salt = user.getUuid();
		//获取user已经储存的密码
		String savePassword = user.getPassword();
		//oldPassword加密
		String newOldpwd = getMd5Password(oldPassword, salt);
		//判断原密码是否一致
		if(savePassword.equals(newOldpwd)) {
			String newNewPwd = getMd5Password(newPassword, salt);
			Integer result = userMapper.changePassword(id, newNewPwd,user.getUsername(),new Date());
			if(result==1) {
				return result;
			}else {
				throw new UserNotFoundException("用户没找到");
			}
		}else {
			throw new PasswordNotMatchException("密码:"+oldPassword+"不正确！");
		}
	}
	
	/**
	 * 用户注册
	 */
	public User reg(User user) {
		User result = findUserByUsername(user.getUsername());
		if(result==null) {
			String uuid = UUID.randomUUID().toString();
			String md5Password = getMd5Password(user.getPassword(), uuid);
			user.setPassword(md5Password);
			user.setUuid(uuid);
			
			Date now = new Date();
			user.setCreateUser(user.getUsername());
			user.setCreateTime(now);
			user.setModifiedUser(user.getUsername());
			user.setModifiedTime(now);
			
			userMapper.insert(user);
			return user;
		}else {
			throw new UsernameConfilictException("用户"+user.getUsername()+"已经存在！");
		}
	}

	/**
	 * 用户登录
	 */
	public User login(String username, String password) throws UserNotFoundException,PasswordNotMatchException{
		User user = findUserByUsername(username);
		if(user!=null) {
			String salt = user.getUuid();
			String newPwd = getMd5Password(password, salt);
			if(user.getPassword().equals(newPwd)) {
				user.setPassword(null);
				user.setUuid(null);
				return user;
			}else {
				throw new PasswordNotMatchException("密码不匹配"+password);
			}
		}else {
			throw new UserNotFoundException("用户名没找到"+username);
		}
	}

	/**
	 * 密码Md5加密
	 */
	public String getMd5Password(String password, String salt) {
		//-----------------------------------------------------------------------------
		//--------------------以下加密规则是自行设计的-------------------
		//-----------------------------------------------------------------------------
		//先把原密码使用MD5加密，并且把密码转换大写
		String str1 = DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
		//密码加盐
		String str2 = str1+salt.toUpperCase();
		//再次加密，得到最终密码
		String str3 = DigestUtils.md5DigestAsHex(str2.getBytes()).toUpperCase();
		//返回
		return str3;
	}
	
	/**
	 * 查询用户信息
	 */
	public User findUserByUsername(String username) {
		User result = null;
		result = userMapper.findUserByUsername(username);
		return result;
	}

	/**
	 * 根据用户ID查询用户信息
	 */
	public User findUserById(Integer id) {
		return userMapper.findUserById(id);
	}

	

}












