package com.jlg.mango.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jlg.mango.dao.CartDao;
import com.jlg.mango.dao.DBlink;
import com.jlg.mango.entity.Cart;

public class CartDAOImple implements CartDao {

	@Override
	public List<Cart> searchCartsByUser_id(int user_id) {
		String sql="select * from cart where user_id = " +user_id;
		List<Cart> carts=new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
//		System.out.println(sql);
		try{
			 conn=DBlink.getInstance().getConnection();
			 ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			 while(rs.next()){
				 Cart cart=new Cart();
				 
				 cart.setId(rs.getInt("id"));
				 cart.setGoods_id(rs.getInt("goods_id"));
				 cart.setGoods_name(rs.getString("goods_name"));
				 cart.setGoods_num(rs.getInt("goods_num"));
				 cart.setUser_id(rs.getInt("user_id"));
				 cart.setStore_id(rs.getInt("store_id"));
				 
				 carts.add(cart);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(conn);
		}
		
		return carts;
	}

	@Override
	public int searchGoodsNumByUserIdAndGoodsId(int user_id, int goods_id, int store_id) {
		String sql="select goods_num from cart where user_id = ? and goods_id = ? and store_id = ?";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			 conn=DBlink.getInstance().getConnection();
			 ps=conn.prepareStatement(sql);
			 
			 ps.setInt(1, user_id);
			 ps.setInt(2, goods_id);
			 ps.setInt(3, store_id);
			 
			 rs=ps.executeQuery();
			 
			 if(rs.next()){
				 return rs.getInt("goods_num");
			 }
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(conn);
		}
		return 0;
	}
	
	
}
