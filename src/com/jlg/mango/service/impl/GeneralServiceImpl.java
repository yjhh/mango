package com.jlg.mango.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.jlg.mango.entity.PageBean;
import com.jlg.mango.service.GeneralService;

/**
 * 通用的服務接口實現類
 * 
 * @author zheng
 *	2016年10月12日 上午10:24:02
 */
public class GeneralServiceImpl implements GeneralService {

	@Override
	public <T> PageBean<T> getPageList(List<T> totalResult, int currentPage, int pageSize) {

		PageBean<T> pageBean = new PageBean<>(currentPage, totalResult.size(), pageSize);
		pageBean.init();
		//总记录
		List<T> list = new ArrayList<T>();
		for(int i=pageBean.getStart()-1;i<pageBean.getEnd();i++){
			list.add(totalResult.get(i));
		}
		
		pageBean.setList(list);
		
		return pageBean;
	}

	

}
