package com.jlg.mango.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jlg.mango.dao.DBlink;
import com.jlg.mango.dao.StoreDao;
import com.jlg.mango.entity.Store;

/**
 * 商铺Dao实现类
 * 
 * @author zheng
 * 2016年10月9日-2016-下午9:35:32
 */
public class StoreDaoImpl implements StoreDao{

	@Override
	public List<Store> searchStoreByName(String name) {
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		String sql = "select * from store where name like ?";
		List<Store> stores = new ArrayList<>();
		
		try {
			conn = DBlink.getInstance().getConnection();
			ptmt = conn.prepareStatement(sql);
			
			//传参
			ptmt.setString(1, "%"+name+"%");
			
			//执行
			ResultSet rs = ptmt.executeQuery();
			
			while(rs.next()){
				stores.add(new Store(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("email"), 
						rs.getString("telNum"), rs.getString("describe"), rs.getInt("status"), rs.getInt("user_id")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		
		return stores;
	}

	@Override
	public Store searchStoreByUserId(int user_id) {
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		String sql = "select * from store where user_id = ?";
		
		try {
			conn = DBlink.getInstance().getConnection();
			ptmt = conn.prepareStatement(sql);
			
			//传参
			ptmt.setInt(1, user_id);
			
			//执行
			ResultSet rs = ptmt.executeQuery();
			
			if (rs.next()){
				return new Store(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("email"), 
						rs.getString("telNum"), rs.getString("describe"), rs.getInt("status"), rs.getInt("user_id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			DBlink.closeConnection(conn);
		}
		
		return null;
	}

	@Override
	public List<Store> searchStoreByStatus(int status) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		String sql = "select * from store where status=?";
		List<Store> stores = new ArrayList<>();
		
		try {
			conn = DBlink.getInstance().getConnection();
			ptmt = conn.prepareStatement(sql);
			
			//传参
			ptmt.setInt(1, status);
			
			//执行
			ResultSet rs = ptmt.executeQuery();
			
			while(rs.next()){
				stores.add(new Store(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("email"), 
						rs.getString("telNum"), rs.getString("describe"), rs.getInt("status"), rs.getInt("user_id")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			DBlink.closeConnection(conn);
		}
		
		return stores;
	}

	@Override
	public Store searchStoreById(int id) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		String sql = "select * from store where id = ?";
		
		try {
			conn = DBlink.getInstance().getConnection();
			ptmt = conn.prepareStatement(sql);
			
			//传参
			ptmt.setInt(1, id);
			
			//执行
			ResultSet rs = ptmt.executeQuery();
			
			if (rs.next()){
				return new Store(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("email"), 
						rs.getString("telNum"), rs.getString("describe"), rs.getInt("status"), rs.getInt("user_id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			DBlink.closeConnection(conn);
		}
		 
		return null;
	}

	@Override
	public int  addreg_shop(String name,String shop_address,String email,String telNum,String shop_info,int status,int user_id) {
		String sql ="INSERT INTO store(name,address,email,telNum,`describe`,status,user_id) "
				+ "VALUES ('"+name+"', '"+shop_address+"', '"+email+"', '"+telNum+"', '"+shop_info+"', '"+status+"', '"+user_id+"');";
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		int count = 0;
		try {
			conn = DBlink.getInstance().getConnection();
			ptmt = conn.prepareStatement(sql);
			//执行
			count = ptmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		return count;
	}

	
	
	
}
