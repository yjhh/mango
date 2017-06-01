package com.jlg.mango.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jlg.mango.dao.DBlink;
import com.jlg.mango.dao.OrderDao;
import com.jlg.mango.entity.Item;
import com.jlg.mango.entity.Order;


/**
 * 
 * @author Yj
 * 订单DAO实现类
 */
public class OrderDaoImpl implements OrderDao {

	@Override
	public List<Order> searchOrder(int use_id) {
		String sql = "select * from `order` where user_id=? and status_id<>4";
		//得到连接
		Connection con = null;
		ResultSet res = null;
		PreparedStatement sta = null;
		List<Order> list = new ArrayList<Order>();
		
		try {
			con = DBlink.getInstance().getConnection();
			sta = con.prepareStatement(sql);
			sta.setInt(1, use_id);
			res = sta.executeQuery();
			while(res.next()){
				Order order = new Order();
				//订单编号
				order.setId(res.getInt("id"));
				//订单时间
				order.setOrderTime(res.getDate("orderTime"));
				//总价
				order.setTotalPrice(res.getDouble("totalPrice"));
				//收货人姓名
				order.setReceive_name(res.getString("recive_name"));
				//收获地址
				order.setFull_address(res.getString("full_address"));
				//手机号
				order.setMobile(res.getString("mobile"));
				//用户编号
				order.setUser_id(res.getInt("user_id"));
				//用户登录名
				order.setUser_logName(new UserDAOImple().searchUserById(res.getInt("user_id")).getLoginName());
				//店铺编号
				order.setStore_id(res.getInt("store_id"));
				//状态编号
				order.setStatus_id(res.getInt("status_id"));
				
				//添加集合
				list.add(order);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(con);
		}
		
		return list;
	}

	@Override
	public List<Item> searchItem(int order_id) {
		String sql = "select * from  Item  where  order_id= ?";
		//得到连接
		Connection con = null;
		ResultSet res = null;
		PreparedStatement sta = null;
		
		List<Item> list = new ArrayList<Item>();
		
		try {
			con = DBlink.getInstance().getConnection();
			sta = con.prepareStatement(sql);
			sta.setInt(1, order_id);
			res = sta.executeQuery();
			while(res.next()){
				Item item = new Item();
				
				//订单编号
				item.setOrder_id(res.getInt("order_id"));
				//商品编号
				item.setGood_id(res.getInt("goods_id"));
				//商品价格
				item.setGoods_price(res.getDouble("goods_price"));
				//购买数量
				item.setGoods_num(res.getInt("goods_num"));
				//查询商品名称
				if(new GoodsDaoImpl().searchStoreGoodsByGood_id(res.getInt("goods_id"))==null){
					item.setGood_name("该商品已删除");
					
				}else{
					item.setGood_name(new GoodsDaoImpl().searchStoreGoodsByGood_id(res.getInt("goods_id")).getName());
				}
				
				
				//查询商品图片
				item.setPics(new GoodsDaoImpl().searchPicture(res.getInt("goods_id")));
				
				//添加进集合
				list.add(item);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭
			DBlink.closeConnection(con);
		}
		
		return list;
	}

	@Override
	public List<Order> searchOrderByStatus_id(int status_id) {
		String sql="select * from `order` where status_id ="+status_id;
		//得到连接
		Connection con = null;
		ResultSet res = null;
		PreparedStatement sta = null;
		List<Order> list = new ArrayList<Order>();
		
		try {
			con = DBlink.getInstance().getConnection();
			sta = con.prepareStatement(sql);
			res = sta.executeQuery();
			while(res.next()){
				Order order = new Order();
				//订单编号
				order.setId(res.getInt("id"));
				//订单时间
				order.setOrderTime(res.getDate("orderTime"));
				//总价
				order.setTotalPrice(res.getDouble("totalPrice"));
				//收货人姓名
				order.setReceive_name(res.getString("recive_name"));
				//收获地址
				order.setFull_address(res.getString("full_address"));
				//手机号
				order.setMobile(res.getString("mobile"));
				//用户编号
				order.setUser_id(res.getInt("user_id"));
				//用户登录名
				order.setUser_logName(new UserDAOImple().searchUserById(res.getInt("user_id")).getLoginName());
				//店铺编号
				order.setStore_id(res.getInt("store_id"));
				//状态编号
				order.setStatus_id(res.getInt("status_id"));
				
				//添加集合
				list.add(order);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(con);
		}
		
		return list;
	}

	@Override
	public List<Order> searchAllOrders() {
		String sql="select * from `order`";
		Connection con = null;
		ResultSet res = null;
		PreparedStatement sta = null;
		List<Order> list = new ArrayList<Order>();
		
		try {
			con = DBlink.getInstance().getConnection();
			sta = con.prepareStatement(sql);
			res = sta.executeQuery();
			while(res.next()){
				Order order = new Order();
				//订单编号
				order.setId(res.getInt("id"));
				//订单时间
				order.setOrderTime(res.getDate("orderTime"));
				//总价
				order.setTotalPrice(res.getDouble("totalPrice"));
				//收货人姓名
				order.setReceive_name(res.getString("recive_name"));
				//收获地址
				order.setFull_address(res.getString("full_address"));
				//手机号
				order.setMobile(res.getString("mobile"));
				//用户编号
				order.setUser_id(res.getInt("user_id"));
				//用户登录名
				order.setUser_logName(new UserDAOImple().searchUserById(res.getInt("user_id")).getLoginName());
				//店铺编号
				order.setStore_id(res.getInt("store_id"));
				//状态编号
				order.setStatus_id(res.getInt("status_id"));
				
				//添加集合
				list.add(order);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(con);
		}
		
		return list;
	}

	@Override
	public List<Order> searchOrderByStore_id(int store_id) {
		String sql = "select * from `order` where store_id=?";
		Connection con = null;
		ResultSet res = null;
		PreparedStatement sta = null;
		List<Order> list = new ArrayList<Order>();
		
		try {
			con = DBlink.getInstance().getConnection();
			sta = con.prepareStatement(sql);
			sta.setInt(1, store_id);
			res = sta.executeQuery();
			while(res.next()){
				Order order = new Order();
				//订单编号
				order.setId(res.getInt("id"));
				//订单时间
				order.setOrderTime(res.getDate("orderTime"));
				//总价
				order.setTotalPrice(res.getDouble("totalPrice"));
				//收货人姓名
				order.setReceive_name(res.getString("recive_name"));
				//收获地址
				order.setFull_address(res.getString("full_address"));
				//手机号
				order.setMobile(res.getString("mobile"));
				//用户编号
				order.setUser_id(res.getInt("user_id"));
				//用户登录名
				order.setUser_logName(new UserDAOImple().searchUserById(res.getInt("user_id")).getLoginName());
				//店铺编号
				order.setStore_id(res.getInt("store_id"));
				//状态编号
				order.setStatus_id(res.getInt("status_id"));
				
				//添加集合
				list.add(order);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(con);
		}
		return list;
	}

	@Override
	public List<Order> searchMyStoreOrderByStatus_id(int store_id, int status_id) {
		String sql = "select * from `order` where store_id=? and status_id=?";
		Connection con = null;
		ResultSet res = null;
		PreparedStatement sta = null;
		List<Order> list = new ArrayList<Order>();
		
		try {
			con = DBlink.getInstance().getConnection();
			sta = con.prepareStatement(sql);
			sta.setInt(1, store_id);
			sta.setInt(2, status_id);
			res = sta.executeQuery();
			while(res.next()){
				Order order = new Order();
				//订单编号
				order.setId(res.getInt("id"));
				//订单时间
				order.setOrderTime(res.getDate("orderTime"));
				//总价
				order.setTotalPrice(res.getDouble("totalPrice"));
				//收货人姓名
				order.setReceive_name(res.getString("recive_name"));
				//收获地址
				order.setFull_address(res.getString("full_address"));
				//手机号
				order.setMobile(res.getString("mobile"));
				//用户编号
				order.setUser_id(res.getInt("user_id"));
				//用户登录名
				order.setUser_logName(new UserDAOImple().searchUserById(res.getInt("user_id")).getLoginName());
				//店铺编号
				order.setStore_id(res.getInt("store_id"));
				//状态编号
				order.setStatus_id(res.getInt("status_id"));
				//添加集合
				list.add(order);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(con);
		}
		return list;
	}

	@Override
	public Order searchOrderByOrder_id(int Order_id) {
		String sql = "select * from `order` where order_id=? ";
		//得到连接
		Connection con = null;
		ResultSet res = null;
		PreparedStatement sta = null;
		List<Order> list = new ArrayList<Order>();
		Order order=new Order();
		try {
			con = DBlink.getInstance().getConnection();
			sta = con.prepareStatement(sql);
			sta.setInt(1,Order_id);
			res = sta.executeQuery();
			if(res.next()){
				//订单编号
				order.setId(res.getInt("id"));
				//订单时间
				order.setOrderTime(res.getDate("orderTime"));
				//总价
				order.setTotalPrice(res.getDouble("totalPrice"));
				//收货人姓名
				order.setReceive_name(res.getString("recive_name"));
				//收获地址
				order.setFull_address(res.getString("full_address"));
				//手机号
				order.setMobile(res.getString("mobile"));
				//用户编号
				order.setUser_id(res.getInt("user_id"));
				//用户登录名
				order.setUser_logName(new UserDAOImple().searchUserById(res.getInt("user_id")).getLoginName());
				//店铺编号
				order.setStore_id(res.getInt("store_id"));
				//状态编号
				order.setStatus_id(res.getInt("status_id"));
				
				//添加集合
				list.add(order);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(con);
		}
		
		return order;
	}

	@Override
	public Order searchOrderByid(int order_id) {
		
		String sql = "select * from `order` where id = ?";
		Connection con = null;
		ResultSet res = null;
		PreparedStatement sta = null;
		Order order = null;
		
		try {
			con = DBlink.getInstance().getConnection();
			sta = con.prepareStatement(sql);
			sta.setInt(1, order_id);
			res = sta.executeQuery();
			if (res.next()){
				order = new Order();
				//订单编号
				order.setId(res.getInt("id"));
				//订单时间
				order.setOrderTime(res.getDate("orderTime"));
				//总价
				order.setTotalPrice(res.getDouble("totalPrice"));
				//收货人姓名
				order.setReceive_name(res.getString("recive_name"));
				//收获地址
				order.setFull_address(res.getString("full_address"));
				//手机号
				order.setMobile(res.getString("mobile"));
				//用户编号
				order.setUser_id(res.getInt("user_id"));
				//用户登录名
				order.setUser_logName(new UserDAOImple().searchUserById(res.getInt("user_id")).getLoginName());
				//店铺编号
				order.setStore_id(res.getInt("store_id"));
				//状态编号
				order.setStatus_id(res.getInt("status_id"));
				
				return order;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBlink.closeConnection(con);
		}
		
		return null;
	}
	
}
