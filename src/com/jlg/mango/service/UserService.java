package com.jlg.mango.service;

import java.util.List;

import com.jlg.mango.entity.Cart;
import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Order;
import com.jlg.mango.entity.ReceiveInfo;
import com.jlg.mango.entity.Store;
import com.jlg.mango.entity.User;

/**
 * 
 * @author Yj
 *
 * 用户Service调用Dao
 */
public interface UserService {
	//查询商品（按商品名模糊查询）
		List<Goods> searchGoodsByName(String goodsName);
		
		//查询店铺（按店铺名模糊查询）
		List<Store> searchStore(String storeName);
		
		//用户注册（增加用户）
		boolean resgin(User user);
		
		//购买商品（生成订单,插入订单表和购物项）
		boolean new_Order(Order order);
		
		/**
		 * 添加购物车商品
		 */
		boolean new_Cart(Cart cart);
		
		/**
		 * 删除购物车商品(根据购物车id)
		 */
		boolean deleteCart(int cart_id);
		
		/**
		 * 更新购物车商品（数量等）
		 */
		boolean updateCartNum(int num, int user_id, int goods_id, int store_id);
		
		//按类别名查询商品
		List<Goods> selectByCategory(String categroyName);
		
		//用户登录成功与否
		User login(String name,String password);
		
		/** 根据用户编号查询购物车 */
		List<Cart> searchCartsByUser_id(int user_id);
		
		
		/*	修改个人信息 loginName telNum address info */
		boolean editUserInfo(User user);
	
		/**
		 * 根据用户编号查询订单
		 */
		List<Order> searchOrderByUser_id(int User_id);
		
		/**
		 * 根据订单id修改订单的状态
		 */
		boolean editOrderStatusByOrder_id(int order_id,int status_id);
		
		/**
		 * 根据用户编号查找收货信息
		 */
		List<ReceiveInfo> searchInfoByUser_id(int user_id);
		
		/** 添加用户收货地址 */
		boolean addReceiveInfo(String name,String address,String phone,int id);
		
		/** 根据订单编号查询订单 */
		Order searchOrderById(int order_id);
		
		void updateUserHead(int id,String fileName);
}
