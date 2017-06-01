package com.jlg.mango.entity;

/**
 * 收货信息实体类
 * 
 * @author zheng
 * 2016年10月9日-2016-下午4:19:08
 */
public class ReceiveInfo {

	private int id;//收货信息编号
	private String name;//收货人姓名
	private String address;//收货地址
	private String telNum;//收货人电话
	private int user_id;//用户编号
	
	public ReceiveInfo() {
	}

	public ReceiveInfo(int id, String name, String address, String telNum, int user_id) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.telNum = telNum;
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
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String toString() {
		return "ReceiveInfo [id=" + id + ", name=" + name + ", address=" + address + ", telNum=" + telNum + ", user_id="
				+ user_id + "]";
	}
	
}
