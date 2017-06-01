package com.jlg.mango.dao;

import java.util.List;

import com.jlg.mango.entity.User;

/**
 * 普通用户接口
 *
 */
public interface UserDao {
	
	/**用户登录 query*/
	User userLogin(String sentence,String password );
	
	/**第一次用户注册（电话和密码）update*/
	int userRegister(User user);
	
	/** 判断登录名是否重复 query */
	boolean  isLogNameExist(String logName);
	
	/**
	 * 判断手机是否使用 query
	 */
	boolean  isTelNumExist(String telNum);
	/**
	 * 判断邮箱是否使用 query
	 */
	boolean  isEmailExist(String email);
	
	/**
	 * 完善个人信息 update
	 */
	int editUserInfo(User user);
	
	/**
	 * 根据id查找user,大概用于个人信息修改，先搜再改再搜
	 */
	User searchUserById(int id);
	
	/** 根据用户名模糊查询用户 */
	List<User> searchUserByName(String name);
	/** 根据用状态查询用户 */
	List<User> searchUserByStatus(int status);
	
	
}
