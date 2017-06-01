package com.jlg.mango.entity;

/**
 * 类别
 */
public class Category {
	
	private int id;// 编号
	private String name;// 名称
	private String describe;// 描述
	private int parent_id;// 父类编号
	
	public Category() {
	}

	public Category(int id, String name, String describe, int parent_id) {
		this.id = id;
		this.name = name;
		this.describe = describe;
		this.parent_id = parent_id;
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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", describe=" + describe + ", parent_id=" + parent_id + "]";
	}

}
