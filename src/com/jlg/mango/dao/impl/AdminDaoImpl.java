package com.jlg.mango.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jlg.mango.dao.AdminDao;
import com.jlg.mango.dao.DBlink;
import com.jlg.mango.entity.Admin;

/**
 * @author zheng
 * 2016年10月9日-2016-上午10:05:46
 */
public class AdminDaoImpl implements AdminDao{

	@Override
	public Admin checkLogin(String loginName, String password) {
		
		Connection conn = null;
		boolean flag = false;
		String sql = "select * from admin where loginName=? ";
		if(password != null){
			sql+="and password=md5(?)";
			flag = true;
		}
		
		PreparedStatement ptmt = null;
		Admin admin = null;
		
		try {
			//获取连接
			conn =  DBlink.getInstance().getConnection();
			
			//获取PreparedStatement对象
			ptmt = conn.prepareStatement(sql);
			
			//传参
			ptmt.setString(1, loginName);
			if(flag)
			ptmt.setString(2, password);
			//执行
			ResultSet rs = ptmt.executeQuery();
			
			//获取查询到的值
			if(rs.next()){
				admin = new Admin(rs.getInt("id"), loginName, 
						rs.getString("name"), rs.getString("password"), rs.getString("sex"), rs.getString("email"), 
						rs.getString("describe"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		
		return admin;
	}

}
