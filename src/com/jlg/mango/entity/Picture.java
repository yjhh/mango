package com.jlg.mango.entity;

/**
 * @author Yj
 * 图片表
 */
public class Picture {
	
	private int id;//图片编号
	private String url;//图片地址url
	private int goods_id;//商品id
	
	public Picture() {
	}

	public Picture(int id, String url, int goods_id) {
		this.id = id;
		this.url = url;
		this.goods_id = goods_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	@Override
	public String toString() {
		return "Picture [id=" + id + ", url=" + url + ", goods_id=" + goods_id + "]";
	}
	
}
