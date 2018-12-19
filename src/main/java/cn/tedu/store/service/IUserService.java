package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConfilictException;

/**
 * 业务层接口
 * @author soft01
 *
 */
public interface IUserService {
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 用户密码
	 * @throws UserNotFoundException 用户未找到
	 * @throws PasswordNotMatchException 用密码不匹配
	 * @return 登录成功返回User对象，登录失败返回null
	 */
	User login(String username,String password) throws UserNotFoundException,PasswordNotMatchException;
	
	/**
	 * 注册用户
	 * @param user 注册的用户数据对象
	 * @throw usernameConflictException 用户名已被占用
	 * @return 注册成功返回User对象
	 */
	User reg(User user);
	/**
	 * 查询用户
	 * @param username 用户名
	 * @return 成功返回匹配的User对象，失败返回null
	 */
	User findUserByUsername(String username);

	/**
	 * 密码md5加密
	 * @param password 用户密码
	 * @param salt 相应的salt
	 * @return  加密后的密码
	 */
	String getMd5Password(String password,String salt);
	
	/**
	 * 根据用户ID查询用户信息
	 * @param id 用户id
	 * @return 查询成功返回封装了用户信息的User对象，否则返回null
	 */
	User findUserById(Integer id);
	
	/**
	 * 修改用户密码
	 * @param id 用户id
	 * @param oldPassword 用户原密码
	 * @param newPassword 用户新密码
	 * @throws UserNotFoundException 用户没找到异常
	 * @throws PasswordNotMatchException 密码不匹配异常
	 * @return 修改成功返回封装了用户信息的User对象，否则返回null
	 * 
	 */
	Integer changePassword(Integer id,String oldPassword,String newPassword)throws UserNotFoundException,PasswordNotMatchException;

	/**
	 * 修改个人信息
	 * @param uid 用户id
	 * @param username 新用户名，如果不修改，则使用null值
	 * @param gender 新用户性别，如果不修改，则使用null值
	 * @param phone 新用户电话，如果不修改，则使用null值
	 * @param avator 新用户头像，如果不修改，则使用null值
	 * @param email 新用户邮箱，如果不修改，则使用null值
	 * @throws UserNotFoundException 抛出用户没找到异常
	 * @throws UsernameConfilictException 抛出用户已经被注册异常
	 * @return 成功返回受影响的行数，失败返回0
	 */
	Integer changeInfo(Integer uid,String avator,String username,Integer gender,String phone,String email)throws UserNotFoundException,UsernameConfilictException;
}




