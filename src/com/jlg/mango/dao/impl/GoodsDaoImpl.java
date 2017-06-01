package com.jlg.mango.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jlg.mango.dao.DBlink;
import com.jlg.mango.dao.GoodsDao;
import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Picture;

/**
 * @author Yj 商品DAO实现类
 */
public class GoodsDaoImpl implements GoodsDao {

	@Override
	public List<Goods> searchGoodsByStoreId(int store_id) {
		String sql = "select * from goods where store_id=? and stock >=0";
		Connection con = null;
		PreparedStatement sta = null;
		ResultSet res = null;
		List<Goods> list = new ArrayList<Goods>();
		try {
			con = DBlink.getInstance().getConnection();
			sta = con.prepareStatement(sql);
			sta.setInt(1, store_id);
			res = sta.executeQuery();
			while (res.next()) {
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
		} finally {
			DBlink.closeConnection(con);
		}

		return list;
	}

	@Override
	public List<Picture> searchPicture(int good_id) {
		String sql = "select * from picture where goods_id=?";
		Connection con = null;
		PreparedStatement sta = null;
		ResultSet res = null;
		List<Picture> list = new ArrayList<Picture>();

		try {
			con = DBlink.getInstance().getConnection();
			sta = con.prepareStatement(sql);
			sta.setInt(1, good_id);
			res = sta.executeQuery();
			while (res.next()) {
				Picture picture = new Picture();
				// 图片编号
				picture.setId(res.getInt("id"));

				// 图片地址
				picture.setUrl(res.getString("url"));

				// 商品编号
				picture.setGoods_id(res.getInt("goods_id"));

				list.add(picture);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(con);
		}

		return list;
	}

	
	@Override
	public List<Goods> searchGoodsByCategoryParentId(int parent_id) {
		String sql = "select * from goods where category_id in (select id from category where parent_id= " + parent_id
				+ " )  and stock>0";

		List<Goods> goods = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods good = new Goods();

				good.setId(rs.getInt("id"));
				good.setName(rs.getString("name"));
				good.setDescribe(rs.getString("describe"));
				good.setCategory_id(rs.getInt("category_id"));
				good.setPrice(rs.getDouble("price"));
				good.setStock(rs.getInt("stock"));
				good.setTime_on_shelves(rs.getDate("time_on_shelves"));
				good.setStore_id(rs.getInt("store_id"));

				goods.add(good);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}

		return goods;
	}

	@Override
	public List<Goods> searchGoodsByCategoryId(int id) {
		String sql = "select * from goods where category_id =" + id+" and stock>0";

		List<Goods> goods = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods good = new Goods();

				good.setId(rs.getInt("id"));
				good.setName(rs.getString("name"));
				good.setDescribe(rs.getString("describe"));
				good.setCategory_id(rs.getInt("category_id"));
				good.setPrice(rs.getDouble("price"));
				good.setStock(rs.getInt("stock"));
				good.setTime_on_shelves(rs.getDate("time_on_shelves"));
				good.setStore_id(rs.getInt("store_id"));

				goods.add(good);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}

		return goods;
	}

	@Override
	public List<Goods> searchGoodsBysentence(String str) {
		//stock=0 下架    stock<0 删除
		String sql = "select * from goods where category_id in (select id from category where `describe` like '%" + str
				+ "%') and stock >0";

		List<Goods> goods = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods good = new Goods();

				good.setId(rs.getInt("id"));
				good.setName(rs.getString("name"));
				good.setDescribe(rs.getString("describe"));
				good.setCategory_id(rs.getInt("category_id"));
				good.setPrice(rs.getDouble("price"));
				good.setStock(rs.getInt("stock"));
				good.setTime_on_shelves(rs.getDate("time_on_shelves"));
				good.setStore_id(rs.getInt("store_id"));

				goods.add(good);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}

		return goods;
	}

	//用户用
	@Override
	public Goods searchGoodsByGood_id(int good_id) {
		String sql = "select * from goods where id =" + good_id+" and stock >0";

		Goods goods = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				goods = new Goods();

				goods.setId(rs.getInt("id"));
				goods.setName(rs.getString("name"));
				goods.setDescribe(rs.getString("describe"));
				goods.setCategory_id(rs.getInt("category_id"));
				goods.setPrice(rs.getDouble("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setTime_on_shelves(rs.getDate("time_on_shelves"));
				goods.setStore_id(rs.getInt("store_id"));

			}
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}

		return goods;
	}

	@Override
	public List<Goods> searchMyByDesc(int store_id, String desc) {
		String sql="select * from goods where `describe` like ? and store_id="+store_id+" and  stock>=0";
		List<Goods> goods = new ArrayList<>();
		String str = "%"+desc+"%";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, str);
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods good = new Goods();

				good.setId(rs.getInt("id"));
				good.setName(rs.getString("name"));
				good.setDescribe(rs.getString("describe"));
				good.setCategory_id(rs.getInt("category_id"));
				good.setPrice(rs.getDouble("price"));
				good.setStock(rs.getInt("stock"));
				good.setTime_on_shelves(rs.getDate("time_on_shelves"));
				good.setStore_id(rs.getInt("store_id"));

				goods.add(good);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}

		return goods;
	}

	//商家用
	@Override
	public Goods searchStoreGoodsByGood_id(int good_id) {
		String sql = "select * from goods where id =" + good_id+" and stock >=0";

		Goods goods = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				goods = new Goods();

				goods.setId(rs.getInt("id"));
				goods.setName(rs.getString("name"));
				goods.setDescribe(rs.getString("describe"));
				goods.setCategory_id(rs.getInt("category_id"));
				goods.setPrice(rs.getDouble("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setTime_on_shelves(rs.getDate("time_on_shelves"));
				goods.setStore_id(rs.getInt("store_id"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}

		return goods;
	}

	/**
	 * 2016-11-18 pdl 修改
	 */
	@Override
	public int searchGood_idByGood(Goods good) {
		String sql="select id from goods where `name`=? and  price=?  and `describe`=?  and  stock=? and time_on_shelves=? and store_id=? and category_id =?";
		Connection con = null;
		PreparedStatement sta = null;
		ResultSet res = null;
		int id=0;

		try {
			con = DBlink.getInstance().getConnection();
			sta = con.prepareStatement(sql);
			sta.setString(1, good.getName());
			sta.setDouble(2, good.getPrice());
			sta.setString(3, good.getDescribe());
			sta.setInt(4, good.getStock());
			sta.setDate(5, good.getTime_on_shelves());
			sta.setInt(6, good.getStore_id());
			sta.setInt(7, good.getCategory_id());
			
			res = sta.executeQuery();
			if (res.next()) {
				id=res.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(con);
		}

		return id;
	}

	@Override
	public Goods searchGoodsByGoodId(int good_id) {
		String sql = "select * from goods where id =" + good_id;

		Goods goods = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				goods = new Goods();

				goods.setId(rs.getInt("id"));
				goods.setName(rs.getString("name"));
				goods.setDescribe(rs.getString("describe"));
				goods.setCategory_id(rs.getInt("category_id"));
				goods.setPrice(rs.getDouble("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setTime_on_shelves(rs.getDate("time_on_shelves"));
				goods.setStore_id(rs.getInt("store_id"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}

		return goods;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		String sql = "select count(1) from goods";
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			if(res.next()){
			count = res.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public List<Integer> getId() {
		// TODO Auto-generated method stub
		String sql = "select id from goods";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs= null;
		List<Integer> list = new ArrayList<>();
		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getInt("id"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	
	
}
