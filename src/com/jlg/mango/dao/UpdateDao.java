package com.jlg.mango.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author zheng
 * 2016年10月9日-2016-上午10:45:57
 */
public class UpdateDao {

	/**
	 * 更新
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public static int  update(String sql,Object [] params) {
		//得到连接
		Connection conn = null;
		int count = 0;
		//执行
		PreparedStatement pstmt = null;
		try {
			conn = DBlink.getInstance().getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			//循环添加参数
			if(params!=null){
				for(int i = 0;i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			//执行
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//关闭连接
		DBlink.closeConnection(conn);
		
		return count;
	}
	
}
