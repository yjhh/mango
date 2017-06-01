package com.jlg.mango.entity;

import java.util.List;

/**
 * 
 * @author Yj 购物项--订单子表
 */
public class Item {

	private int order_id;// 订单编号
	private int good_id;// 商品编号
	private double goods_price;// 商品价格
	private int goods_num;// 商品数量
	private String good_name;//商品名称
	private List<Picture> pics;//商品的图片
	
	public Item() {
	}

	

	public List<Picture> getPics() {
		return pics;
	}



	public void setPics(List<Picture> pics) {
		this.pics = pics;
	}



	public Item(int order_id, int good_id, double goods_price, int goods_num, String good_name, List<Picture> pics) {
		super();
		this.order_id = order_id;
		this.good_id = good_id;
		this.goods_price = goods_price;
		this.goods_num = goods_num;
		this.good_name = good_name;
		this.pics = pics;
	}



	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getGood_id() {
		return good_id;
	}

	public void setGood_id(int good_id) {
		this.good_id = good_id;
	}

	public double getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}

	public int getGoods_num() {
		return goods_num;
	}

	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}

	public String getGood_name() {
		return good_name;
	}

	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}



	@Override
	public String toString() {
		return "Item [order_id=" + order_id + ", good_id=" + good_id + ", goods_price=" + goods_price + ", goods_num="
				+ goods_num + ", good_name=" + good_name + ", pics=" + pics + "]";
	}

	

}
