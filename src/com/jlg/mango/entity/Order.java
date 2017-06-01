package com.jlg.mango.entity;

import java.sql.Date;
import java.util.List;

/**
 * @author Yj 订单表
 */
public class Order {
	
	private int id;// 订单编号
	private Date orderTime;// 订单生成时间
	private double totalPrice;// 总价
	private String receive_name;// 收货人姓名
	private String full_address;// 收货地址
	private String mobile;// 收货电话
	private int user_id;// 用户id
	private String user_logName;//用户登录名
	private int store_id;// 店铺编号
	private int status_id=1;// 状态编号
	private List<Item> list;// 购物项

	public Order() {
	}
	public String getUser_logName() {
		return user_logName;
	}

	public void setUser_logName(String user_logName) {
		this.user_logName = user_logName;
	}

	public Order(int id, Date orderTime, double totalPrice, String receive_name, String full_address, String mobile,
			int user_id, String user_logName, int store_id, int status_id, List<Item> list) {
		super();
		this.id = id;
		this.orderTime = orderTime;
		this.totalPrice = totalPrice;
		this.receive_name = receive_name;
		this.full_address = full_address;
		this.mobile = mobile;
		this.user_id = user_id;
		this.user_logName = user_logName;
		this.store_id = store_id;
		this.status_id = status_id;
		this.list = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getReceive_name() {
		return receive_name;
	}

	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}

	public String getFull_address() {
		return full_address;
	}

	public void setFull_address(String full_address) {
		this.full_address = full_address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public List<Item> getList() {
		return list;
	}

	public void setList(List<Item> list) {
		this.list = list;
	}
	
	

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderTime=" + orderTime + ", totalPrice=" + totalPrice + ", receive_name="
				+ receive_name + ", full_address=" + full_address + ", mobile=" + mobile + ", user_id=" + user_id
				+ ", store_id=" + store_id + ", status_id=" + status_id + ", list=" + list + "]";
	}
	

}
