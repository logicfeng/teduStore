package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

/**
 * 持久层的接口
 * @author soft01
 *
 */
public interface UserMapper {
	
	/**
	 * 插入用户信息
	 * @param user 封装了用户信息
	 * @return 成功返回受影响的行数，失败返回0
	 */
	Integer insert(User user);

	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 查询成功返回封装了用户信息的User对象，否则返回null
	 */
	User findUserByUsername(String username);
	
	/**
	 * 根据用户ID查询用户信息
	 * @param id 用户id
	 * @return 查询成功返回封装了用户信息的User对象，否则返回null
	 */
	User findUserById(Integer id);
	
	/**
	 * 更改密码
	 * @param id 用户id
	 * @param password 用户密码
	 * @return 成功返回受影响的行数，失败返回0
	 */
	Integer changePassword(@Param("id")Integer id,@Param("password")String password,
			@Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 修改个人信息
	 * @param user 封装了被修改的用户id（必选）、新用户名（可选）、邮箱（可选）、电话（）
	 * @return 成功返回受影响的行数，失败返回0
	 */
	Integer changeInfo(User user);
}










