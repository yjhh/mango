package com.jlg.mango.entity;

/**
 * 商铺实体类
 * 
 * @author zheng 2016年10月9日-2016-下午4:10:27
 */
public class Store {

	private int id; // 商铺编号
	private String name;// 商铺地址
	private String address;// 商铺地址
	private String email;// 商铺邮箱
	private String telNum;// 商铺电话
	private String describe;// 商铺描述
	private int status;// 商铺状态
	private int user_id;// 用户编号

	public Store() {
	}

	public Store(int id, String name, String address, String email, String telNum, String describe, int status,
			int user_id) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.telNum = telNum;
		this.describe = describe;
		this.status = status;
		this.user_id = user_id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", telNum=" + telNum
				+ ", describe=" + describe + ", status=" + status + ", user_id=" + user_id + "]";
	}

}
