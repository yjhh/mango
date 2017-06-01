package com.jlg.mango.dao;

import java.util.List;

import com.jlg.mango.entity.Item;
import com.jlg.mango.entity.Order;

/**
 * 订单DAO接口
 * 
 * @author Yj
 */
public interface OrderDao {
	/**通过用户id查询订单主表
	 * 
	 */
	public abstract List<Order> searchOrder(int use_id);
	/**通过订单编号查询购物项
	 * 
	 */
	public List<Item> searchItem(int order_id);
	
	/**通过状态查询订单
	 * 

	 */
	public List<Order> searchOrderByStatus_id(int status_id);
	
	/**查询所有订单
	 * 	 */
	public List<Order> searchAllOrders();
	
	/**
	 * 根据店铺id查询订单

	 */
	public List<Order> searchOrderByStore_id(int store_id);
	
	/**根据店铺id和订单状态查询订单
	 * 
	 */
	public List<Order> searchMyStoreOrderByStatus_id(int store_id,int status_id);
	
	/**
	 * 根据订单编号查询订单
	 */
	public  Order searchOrderByOrder_id(int Order_id);
	
	/** 根据编号查询订单 */
	public Order searchOrderByid(int order_id);
	
}
