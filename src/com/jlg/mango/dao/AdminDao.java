package com.jlg.mango.dao;

import com.jlg.mango.entity.Admin;


/**
 * 系统管理员Dao接口
 * 
 * @author zheng
 * 2016年10月9日-2016-上午9:05:06
 */
public interface AdminDao {

	/** 登陆（验证用户名密码,有则返回） */
	Admin checkLogin(String loginName, String password);
	
}
