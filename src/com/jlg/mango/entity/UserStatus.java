package com.jlg.mango.entity;

public enum UserStatus {
	NORMAL(0,"正常"),
	COOL(1,"冻结");
	private int status;
	private String desc;
	public String getDesc(){
		return desc;
	}
	public int getStatus(){
		return status;
	}
	private UserStatus(int status,String desc){
		this.status = status;
		this.desc = desc;
	}
}
