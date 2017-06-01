package com.jlg.mango.service;

import java.util.List;

import com.jlg.mango.entity.PageBean;

/**
 * 通用的服務接口
 * 
 * @author zheng
 *	2016年10月12日 上午10:19:37
 */
public interface GeneralService {

	/** Page分頁方法 
	 * 		通用的分頁方法  -- 前臺分頁
	 * @param <T>
	 */
	<T> PageBean<T> getPageList(List<T> totalResult, int currentPage, int pageSize);
	
}
