package com.jlg.mango.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jlg.mango.dao.CategoryDao;
import com.jlg.mango.dao.DBlink;
import com.jlg.mango.entity.Category;
import com.jlg.mango.entity.Goods;

/**
 * @author zheng
 * 2016年10月9日-2016-下午7:52:06
 */
public class CategoryDaoImpl implements CategoryDao{

	@Override
	public List<Category> searchAllCategory() {
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		
		String sql = "select * from category";
		
		List<Category> categories = new ArrayList<>();
		
		try {
			conn = DBlink.getInstance().getConnection();
			ptmt = conn.prepareStatement(sql);
			
			//执行
			ResultSet rs = ptmt.executeQuery();
			
			//循环取出
			while(rs.next()){
				categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("describe"), rs.getInt("parent_id")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(conn);
		}
		
		return categories;
	}

	@Override
	public List<Integer> searchCategoryByParentId(int parent_id) {
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		
		String sql = "select id from category where parent_id=?";
		
		List<Integer> childIds = new ArrayList<>();
		
		try {
			conn = DBlink.getInstance().getConnection();
			ptmt = conn.prepareStatement(sql);
			
			//传参
			ptmt.setInt(1, parent_id);
			
			//执行
			ResultSet rs = ptmt.executeQuery();
			
			//循环取出
			while(rs.next()){
				childIds.add(rs.getInt("id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(conn);
		}
		
		return childIds;
	}

	@Override
	public List<Goods> selectByCategory(String categroyName) {
		String sql = "select * from goods where category_id=(select id from category where name=?)";
		Connection con = null;
		PreparedStatement sta = null;
		ResultSet res = null;
		List<Goods> list = new ArrayList<>();
		
		try {
			con = DBlink.getInstance().getConnection();
			sta = con.prepareStatement(sql);
			sta.setString(1, categroyName);
			res = sta.executeQuery();
			while(res.next()){
				Goods good = new Goods();
				// 添加商品编号
				good.setId(res.getInt("id"));
				// 添加商品名
				good.setName(res.getString("name"));
				// 添加价格
				good.setPrice(res.getDouble("price"));
				// 商品库存
				good.setStock(res.getInt("stock"));
				// 商品描述
				good.setDescribe(res.getString("describe"));
				// 上架时间
				good.setTime_on_shelves(res.getDate("time_on_shelves"));
				// 商铺编号
				good.setStore_id(res.getInt("store_id"));
				// 类别编号
				good.setCategory_id(res.getInt("category_id"));

				// 添加到集合
				list.add(good);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(con);
		}
		
		return list;
	}

	@Override
	public List<Category> searchCategoryByParent_id(int parent_id) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		
		String sql = "select * from category where parent_id=?";
		
		List<Category> categories = new ArrayList<>();
		
		try {
			conn = DBlink.getInstance().getConnection();
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, parent_id);
			//执行
			ResultSet rs = ptmt.executeQuery();
			
			//循环取出
			while(rs.next()){
				categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("describe"), rs.getInt("parent_id")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(conn);
		}
		
		return categories;
	}

	@Override
	public List<Category> searchCategoryByid(int id) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		
		String sql = "select * from category where id=?";
		
		List<Category> categories = new ArrayList<>();
		
		try {
			conn = DBlink.getInstance().getConnection();
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, id);
			//执行
			ResultSet rs = ptmt.executeQuery();
			
			//循环取出
			while(rs.next()){
				categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("describe"), rs.getInt("parent_id")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(conn);
		}
		
		return categories;
	}

}
