package com.jlg.mango.entity;

import java.util.List;

/**
 * 分页显示的标准类,基本操作,是先给予-当前页数一共的数据条数-每页显示的条数, 然后在初始化该类,得到总共页数,和开始序号和结束序号,
 * 然后数据库分页用到开始序号和结束序号,得到数据集合后赋值给该类的list属性,
 * 
 * @author zheng 2016年10月11日 下午10:14:25
 */
public class PageBean<T> {

	private int rowsCount;	// 总记录数
	private int pageCount;	// 总页数
	private int pageSize;	// 每页的数据条数
	private int currentPage;// 当前页

	private int start;		// 起始数据位置
	private int end;		// 结束

	private List<T> list = null;

	public void init() {
		/*
		 * 根据	总记录数rowsCount 和	每页的数据条数pageSize计算	页数pageCount
		 */
		int pageSize_x = (int) rowsCount / pageSize;
			this.pageCount = rowsCount % pageSize == 0 ? pageSize_x : pageSize_x + 1;
			
		// 判断页数和当前页数
		if (currentPage > pageCount) {
			currentPage = pageCount;
		}
		if (currentPage < 1) {
			currentPage = 1;
		}
		
		// 根据当前页计算起始和结束条目
		this.start = (currentPage - 1) * pageSize + 1;
		//判断结束是否大于总记录数
		this.end = currentPage * pageSize>=rowsCount?rowsCount:currentPage * pageSize;
	}

	public PageBean(int currentPage, int rowsCount, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.rowsCount = rowsCount;
		this.pageSize = pageSize;
	}

	public PageBean(int currentPage, int rowsCount, int pageSize, List<T> list) {
		super();
		this.currentPage = currentPage;
		this.rowsCount = rowsCount;
		this.pageSize = pageSize;
		this.list = list;
	}

	public PageBean() {
		super();
	}

	public int getRowsCount() {
		return rowsCount;
	}

	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean [rowsCount=" + rowsCount + ", pageCount=" + pageCount + ", pageSize=" + pageSize
				+ ", currentPage=" + currentPage + ", start=" + start + ", end=" + end + ", list=" + list + "]";
	}

}