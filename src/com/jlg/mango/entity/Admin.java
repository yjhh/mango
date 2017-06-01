package com.jlg.mango.entity;

/**
 * 系统管理员实体类
 * 
 * @author zheng
 * 2016年10月9日-2016-上午9:08:54
 */
public class Admin {

	private int id;		//管理员编号
	private String loginName; 	//管理员登录名
	private String name; 	//管理员名称
	private String password;	//管理员密码
	private String sex; //性别
	private String email; //邮箱
	private String describe="这个人很懒，什么都没写"; //描述（备注等）
	
	public Admin() {
	}

	public Admin(int id, String loginName, String name, String password, String sex, String email, String describe) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.email = email;
		this.describe = describe;
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
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", loginName=" + loginName + ", name=" + name + ", password=" + password + ", sex="
				+ sex + ", email=" + email + ", describe=" + describe + "]";
	}
	
}
