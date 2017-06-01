package com.jlg.mango.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jlg.mango.dao.DBlink;
import com.jlg.mango.dao.ReceiveInfoDAO;
import com.jlg.mango.entity.ReceiveInfo;

public class ReceiveInfoDAOImple implements ReceiveInfoDAO {

	@Override
	public List<ReceiveInfo> searchInfoByUser_id(int user_id) {
		String sql="select * from receiveInfo where user_id = " +user_id;
		List<ReceiveInfo> infos=new ArrayList<>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			 conn=DBlink.getInstance().getConnection();
			 ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			 while(rs.next()){
				ReceiveInfo info=new ReceiveInfo();
				
				info.setId(rs.getInt("id"));
				info.setAddress(rs.getString("address"));
				info.setName(rs.getString("name"));
				info.setTelNum(rs.getString("telNum"));
				info.setUser_id(rs.getInt("user_id"));
				
				infos.add(info);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(conn);
		}
		
		return infos;
	}
	
	//添加收货地址
	public int addReceiveInfo(String name,String address,String phone,int id) {
		String sql ="insert into `receiveinfo`(address,name,telNum,user_id) values(?,?,?,?)";
		int count = 0;
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			 
			 ps.setString(1, address);
			 ps.setString(2, name);
			 ps.setString(3, phone);
			 ps.setInt(4, id);;
			 
			 count=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
//		System.out.println(sql);
		return count;
	}
	
	//根据用户信息删除收货地址
	public int  delReceiveInfo(int id) {
		String sql ="delete from  `receiveinfo` where id = "+id+"";
		int count = 0;
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			count=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		return count;
	}
}
