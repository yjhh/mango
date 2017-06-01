package com.jlg.mango.service;

import java.util.List;

import com.jlg.mango.entity.Admin;
import com.jlg.mango.entity.Category;
import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Order;
import com.jlg.mango.entity.Store;
import com.jlg.mango.entity.User;

/**
 * 管理员Service接口
 * 		调用底层Dao
 * 
 * @author zheng
 * 2016年10月9日-2016-下午10:24:41
 */
public interface AdminService {
	
	//验证登录
	Admin login(String username,String password);

	//操作普通用户
	/** 删除用户 */
	boolean deleteUserById(int id);
	
	/** 更改用户状态（冻结，解冻） */
	boolean updateUserStatusById(int id, int status);
	
	/** 查询用户（通过用户编号查询） */
	User searchUserById(int id);
	
	/** 根据用户名模糊查询用户 */
	List<User> searchUserByName(String name);
	
	/*根据用户状态查询用户*/
	List<User> searchUserByStatus(int status);
	
	
	//操作商铺
	/** 删除商铺（根据商铺编号） */
	boolean deleteStoreById(int id);
	
	/** 更改商铺状态（冻结？）审核新商铺申请（增加商铺记录） */
	boolean updateStoreStatusById(int id, int status);
	
	/** 查询所有商铺（模糊） */
	List<Store> searchStoreByName(String name);
	
	/** 根据用户编号查询商铺 */
	Store searchStoreByUserId(int user_id);
	
	/** 根据店铺状态查询商铺 */
	List<Store> searchStoreByStatus(int status);
	
	
	//类别操作
	/** 查找类别（所有） */
	List<Category> searchAllCategory();
	
	/** 父类编号查询子类 */
	List<Category> searchCategoryByParent_Id(int parent_id);
	
	/*查询类别 根据编号*/
	List<Category> searchCategoryByid(int id);
	
	
	/** 根据父类编号查询所属子类编号 */
	List<Integer> searchCategoryByParentId(int parent_id);
	
	/** （忽略）添加类别 */
	/** （忽略）删除类别 */
	
	//订单操作
	/** 查询所有订单信息 */
	List<Order> searchAllOrders();
	
	/** 通过状态查询订单 */
	List<Order> searchOrderByStatus_id(int status_id);
	
	/** 通过店铺id查询订单 */
	List<Order> searchOrderByStore_id(int store_id);
	
	/**
	 * 根据父类条件名称查询商品，如果只点击了父类条件
	 */
	List<Goods> searchGoodsByCategoryParentId(int parent_id);
	
	/**
	 * 如果点击了子类条件
	 */
	List<Goods> searchGoodsByCategoryId(int id);
	
}
