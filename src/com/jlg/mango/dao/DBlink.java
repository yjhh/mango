package com.jlg.mango.dao;


import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 * @author zheng
 * 2016年10月9日-2016-上午10:10:59
 */

public class DBlink {
	
	private static DBlink db;
	
	//私有的构造方法  -- 单例模式
	private DBlink(){
	}
	
	//获取连接
	public static DBlink getInstance(){
		if(db==null){
			db = new DBlink();
		}
		return db;
	}
	
	/**
	 * 得到连接
	 * @return
	 */
	public Connection getConnection() throws Exception{
		DataSource ds = (DataSource)new InitialContext().lookup("java:comp/env/mango");
		return  ds.getConnection();
	}
	
	
	/**
	 * 关闭连接
	 */
	public static void closeConnection(Connection conn){
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}










