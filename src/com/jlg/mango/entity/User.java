package com.jlg.mango.entity;

import java.sql.Date;

import com.jlg.mango.utils.Tools;

/**
 * normalUser普通用户
 *
 */
public class User {

	private int id; // 用户编号
	private String loginName; // 用户登录名
	private String name="游客"; // 用户姓名
	private String password; // 用户密码
	private String sex="男"; // 用户性别
	private String email="还没有邮箱哦"; // .用户邮箱
	private String telNum; // 用户电话
	private String address="壮哉我大中华"; // 用户地址
	private Date register_time=new Tools().String_To_SqlDate(new java.util.Date()); // 用户注册时间
	private String icon="default_head.jpg"; // 用户头像
	private int status=0; // .用户状态（冻结1/解冻0）
	private String info="这个人很懒，什么都没写";; // 用户描述

	public User() {
	}

	/**
	 * 用于第一次直接注册
	 */
	public User(String telNum, String password) {
		this.telNum = telNum;
		this.password = password;
	}

	/**
	 * 用于用户修改个人信息， PS:id不可修改，status不可修改，注册时间不可修改
	 * 
	 */
	public User(int id, String loginName, String name, String password, String sex, String email, String telNum,
			String address, Date register_time, String icon, int status, String info) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.email = email;
		this.telNum = telNum;
		this.address = address;
		this.register_time = register_time;
		this.icon = icon;
		this.status = status;
		this.info = info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegister_time() {
		return register_time;
	}

	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", name=" + name + ", password=" + password + ", sex="
				+ sex + ", email=" + email + ", telNum=" + telNum + ", address=" + address + ", register_time="
				+ register_time + ", icon=" + icon + ", status=" + status + ", info=" + info + "]";
	}

}
