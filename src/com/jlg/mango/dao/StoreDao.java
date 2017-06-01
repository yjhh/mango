package com.jlg.mango.dao;

import java.util.List;

import com.jlg.mango.entity.Store;

/**
 * 商铺Dao接口
 * 
 * @author zheng
 * 2016年10月9日-2016-下午8:08:52
 */
public interface StoreDao {

	/** 查询商铺（根据姓名模糊查询） */
	List<Store> searchStoreByName(String name);
	
	/** 根据用户编号查询商铺 */
	Store searchStoreByUserId(int user_id);
	
	/** 根据店铺状态查询商铺 */
	List<Store> searchStoreByStatus(int status);
	
	/** 根据店铺编号查询店铺 */
	Store searchStoreById(int id);
	
 int  addreg_shop(String name,String shop_address,String email,String telNum,String shop_info,int status,int user_id);
	
}
