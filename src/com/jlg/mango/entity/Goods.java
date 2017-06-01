package com.jlg.mango.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yj 商品实体
 */

public class Goods {

	private int id;// 商品编号
	private String name;// 商品名
	private double price;// 商品价格
	private int stock;// 库存
	private String describe;// 描述
	private Date time_on_shelves;// 上架时间
	private int store_id;// 商铺编号
	private int category_id;// 类别编号
	private List<Picture> list = new ArrayList<Picture>();;// 商品图片
	
	public Goods() {
	}

	public Goods(int id, String name, double price, int stock, String describe, Date time_on_shelves, int store_id,
			int category_id, List<Picture> list) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.describe = describe;
		this.time_on_shelves = time_on_shelves;
		this.store_id = store_id;
		this.category_id = category_id;
		this.list = list;
	}
	public Goods(String name, double price, int stock, String describe, Date time_on_shelves, int store_id,
			int category_id) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.describe = describe;
		this.time_on_shelves = time_on_shelves;
		this.store_id = store_id;
		this.category_id = category_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Date getTime_on_shelves() {
		return time_on_shelves;
	}

	public void setTime_on_shelves(Date time_on_shelves) {
		this.time_on_shelves = time_on_shelves;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public List<Picture> getList() {
		return list;
	}

	public void setList(List<Picture> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + ", describe=" + describe
				+ ", time_on_shelves=" + time_on_shelves + ", store_id=" + store_id + ", category_id=" + category_id
				+ ", list=" + list + "]";
	}

	
}
