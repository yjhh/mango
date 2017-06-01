package com.jlg.mango.entity;

/**
 * 购物车实体类
 * 
 * @author zheng
 * 2016年10月9日-2016-下午4:23:33
 */
public class Cart {

	private int id;// 记录编号
	private int user_id;// 用户编号
	private int goods_id;// 商品编号
	private int store_id;// 商铺编号
	private String goods_name;// 商品名称
	private int goods_num;// 商品数量

	public Cart() {
	}

	public Cart(int id, int user_id, int goods_id, int store_id, String goods_name, int goods_num) {
		this.id = id;
		this.user_id = user_id;
		this.goods_id = goods_id;
		this.store_id = store_id;
		this.goods_name = goods_name;
		this.goods_num = goods_num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public int getGoods_num() {
		return goods_num;
	}

	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user_id=" + user_id + ", goods_id=" + goods_id + ", store_id=" + store_id
				+ ", goods_name=" + goods_name + ", goods_num=" + goods_num + "]";
	}

	
}
