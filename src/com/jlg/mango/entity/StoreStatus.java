package com.jlg.mango.entity;

public enum StoreStatus {
	NORMAL(0,"正常"),
	COOL(1,"冻结"),
	INIT(2,"审核中"),
	OUT(3,"注销"),
	OUTFOR(4,"申请注销"),
	;
	private int status;
	private String desc;
	public String getDesc(){
		return desc;
	}
	public int getStatus(){
		return status;
	}
	private StoreStatus(int status,String desc){
		this.status = status;
		this.desc = desc;
	}
}
