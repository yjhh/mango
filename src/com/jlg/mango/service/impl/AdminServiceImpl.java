package com.jlg.mango.service.impl;

import java.util.List;

import com.jlg.mango.dao.OrderDao;
import com.jlg.mango.dao.UpdateDao;
import com.jlg.mango.dao.impl.AdminDaoImpl;
import com.jlg.mango.dao.impl.CategoryDaoImpl;
import com.jlg.mango.dao.impl.GoodsDaoImpl;
import com.jlg.mango.dao.impl.OrderDaoImpl;
import com.jlg.mango.dao.impl.StoreDaoImpl;
import com.jlg.mango.dao.impl.UserDAOImple;
import com.jlg.mango.entity.Admin;
import com.jlg.mango.entity.Category;
import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Order;
import com.jlg.mango.entity.Store;
import com.jlg.mango.entity.User;
import com.jlg.mango.service.AdminService;

/**
 * @author zheng
 *	2016年10月10日 下午7:27:57
 */
public class AdminServiceImpl implements AdminService{

	@Override 
	public boolean deleteUserById(int id) {
		String sql = "delete from `user` where id = ?";
		Object[] params = new Object[]{id};
		int count = UpdateDao.update(sql, params);
		
		return count>0?true:false;
	}

	@Override
	public boolean updateUserStatusById(int id, int status) {
		/** 更改用戶狀態 （包括删除时，给一个已删除状态）*/
		String sql = "update user set status = ? where id = ?";
		Object[] params = new Object[]{status, id};
		
		int count = 0;
		count = UpdateDao.update(sql, params);
		
		return count>0?true:false;
	}

	@Override
	public User searchUserById(int id) {
		return new UserDAOImple().searchUserById(id);
	}

	@Override
	public List<User> searchUserByName(String name) {
		return new UserDAOImple().searchUserByName(name);
	}

	@Override
	public boolean deleteStoreById(int id) {
		String sql = "delete from store where id = ?";
		Object[] params = new Object[]{id};
		
		int count = 0;
		count = UpdateDao.update(sql, params);
		
		return count>0?true:false;
	}

	@Override
	public boolean updateStoreStatusById(int id, int status) {
		String sql = "update store set status = ? where id = ?";
		Object[] params = new Object[]{status, id};
		
		int count = 0;
		count = UpdateDao.update(sql, params);
		
		return count>0?true:false;
	}

	@Override
	public List<Store> searchStoreByName(String name) {
		return new StoreDaoImpl().searchStoreByName(name);
	}

	@Override
	public Store searchStoreByUserId(int user_id) {
		return new StoreDaoImpl().searchStoreByUserId(user_id);
	}

	@Override
	public List<Category> searchAllCategory() {
		return new CategoryDaoImpl().searchAllCategory();
	}

	@Override
	public List<Integer> searchCategoryByParentId(int parent_id) {
		return new CategoryDaoImpl().searchCategoryByParentId(parent_id);
	}

	@Override
	public List<Order> searchAllOrders() {
		OrderDao order = new OrderDaoImpl();
		//查询所有订单，订单主表
		List<Order> list = order.searchAllOrders();
		for(int i=0;i<list.size();i++){
			//根据订单编号查询购物项
			list.get(i).setList(order.searchItem(list.get(i).getId()));
		}
		return list;
	}

	@Override
	public List<Order> searchOrderByStatus_id(int status_id) {
		OrderDao order = new OrderDaoImpl();
		//查询所有订单，订单主表
		List<Order> list = order.searchOrderByStatus_id(status_id);
		for(int i=0;i<list.size();i++){
			//根据订单编号查询购物项
			list.get(i).setList(order.searchItem(list.get(i).getId()));
		}
		return list;
	}

	@Override
	public List<Order> searchOrderByStore_id(int store_id) {
		OrderDao order = new OrderDaoImpl();
		//查询所有订单，订单主表
		List<Order> list = order.searchOrderByStore_id(store_id);
		for(int i=0;i<list.size();i++){
			//根据订单编号查询购物项
			list.get(i).setList(order.searchItem(list.get(i).getId()));
		}
		return list;
	}

	@Override
	public List<User> searchUserByStatus(int status) {
		return new UserDAOImple().searchUserByStatus(status);
	}

	@Override
	public List<Store> searchStoreByStatus(int status) {
		return new StoreDaoImpl().searchStoreByStatus(status);
	}

	@Override
	public List<Category> searchCategoryByParent_Id(int parent_id) {
		return new CategoryDaoImpl().searchCategoryByParent_id(parent_id);
	}

	@Override
	public Admin login(String username,String password) {
		// TODO Auto-generated method stub
		return new AdminDaoImpl().checkLogin(username, password);
	}

	@Override
	public List<Category> searchCategoryByid(int id) {
		// TODO Auto-generated method stub
		return new CategoryDaoImpl().searchCategoryByid(id);
	}

	@Override
	public List<Goods> searchGoodsByCategoryParentId(int parent_id) {
		// TODO Auto-generated method stub
		return new GoodsDaoImpl().searchGoodsByCategoryParentId(parent_id);
	}

	@Override
	public List<Goods> searchGoodsByCategoryId(int id) {
		// TODO Auto-generated method stub
		return new GoodsDaoImpl().searchGoodsByCategoryId(id);
	}

	
	
}
