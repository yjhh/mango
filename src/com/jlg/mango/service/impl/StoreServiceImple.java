package com.jlg.mango.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.jlg.mango.dao.GoodsDao;
import com.jlg.mango.dao.OrderDao;
import com.jlg.mango.dao.UpdateDao;
import com.jlg.mango.dao.impl.GoodsDaoImpl;
import com.jlg.mango.dao.impl.OrderDaoImpl;
import com.jlg.mango.dao.impl.StoreDaoImpl;
import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Item;
import com.jlg.mango.entity.Order;
import com.jlg.mango.entity.Picture;
import com.jlg.mango.entity.Store;
import com.jlg.mango.service.StoreService;

public class StoreServiceImple implements StoreService {

	@Override
	public boolean addGoods(Goods good) {
		String sql="insert into goods ( `name`,  price,  stock, `describe`,  time_on_shelves,  store_id,  category_id )  values (?,?,?,?,?,?,?)";
		Object [] params=new Object[]{
				good.getName(),good.getPrice(),good.getStock(),good.getDescribe(),
				good.getTime_on_shelves(),good.getStore_id(),good.getCategory_id()
		};
		int result=UpdateDao.update(sql, params);
		return result>0?true:false;
	}
	
	@Override
	public boolean addPictures(int goodsId, String url) {
		String sql = "insert into picture (url,goods_id) values(?,?)";
		Object[] params = new Object[]{url,goodsId};
		
		int result = UpdateDao.update(sql, params);
		return result>0?true:false;
	}

	@Override
	public boolean delGoods(int id) {
		String sql="delete from goods where id ="+id;
		Object [] params=new Object[]{id};
		int result=UpdateDao.update(sql, params);
		return result>0?true:false;
	}

	@Override
	public boolean editGoods(Goods good) {
		String sql="update  goods  set   `name` = ?,  price=?,  stock=?,  `describe`=?,  time_on_shelves=?, store_id=?,  category_id=?  where  id =?";
		                //update goods set stock = ? , `describe` = ? , `name` = ? , price = ? , time_on_shelves = ? , store_id = ? , category_id = ? where id = ?
		System.out.println(good.toString());
		Object [] params=new Object[]{
				good.getName(),good.getPrice(),good.getStock(),good.getDescribe(),
				good.getTime_on_shelves(),good.getStore_id(),good.getCategory_id(),good.getId()
		};
		int result=UpdateDao.update(sql, params);
		return result>0?true:false;
	}

	@Override
	public List<Goods> searchMyStoreGoods(int store_id) {
		GoodsDao gd=new GoodsDaoImpl();
		List<Goods> goods=gd.searchGoodsByStoreId(store_id);
		return goods;
	}

	@Override
	public List<Order> searchOrders(int status_id){
		OrderDao od=new OrderDaoImpl();
		List<Order> orders=od.searchOrderByStatus_id(status_id);
		return orders;
	}

	@Override
	public boolean updateOrders(Order order) {
		String sql="update `order` set status_id =? where id = ?";
		Object[] params=new Object[]{order.getStatus_id(),order.getId()};
		int result=UpdateDao.update(sql, params);
		return result>0?true:false;
	}

	@Override
	public boolean updateOrder(Order order) {
		String sql="update `order` set receive_name = ?,full_address = ?,mobile = ?";
		Object[] params=new Object[]{order.getReceive_name(),order.getFull_address(),order.getMobile()};
		int result=UpdateDao.update(sql, params);
		return result>0?true:false;
	}

	@Override
	public List<Order> searchOrders() {
		OrderDao od=new OrderDaoImpl();
		List<Order> orders=od.searchAllOrders();
		return orders;
	}

	@Override
	public boolean editStoreStatus(int status,int user_id) {
		String sql="update store set status = ?  where id=?";
		Object[] params=new Object[]{status,user_id};
		int result=UpdateDao.update(sql, params);
		return result>0?true:false;
	}

	@Override
	public List<Picture> searchPicture(int good_id) {
		GoodsDao gd=new GoodsDaoImpl();
		List<Picture> pics=gd.searchPicture(good_id);
		return pics;
	}

	@Override
	public Goods searchGoodsById(int id) {
		return new GoodsDaoImpl().searchGoodsByGood_id(id);
	}


	@Override
	public List<Item> searchItemByOrder_id(int order_id) {
		
		return new OrderDaoImpl().searchItem(order_id);
	}

	@Override
	public List<Order> searchMyStoreOrders(int store_id) {
		OrderDao od=new OrderDaoImpl();
		List<Order> orders=od.searchOrderByStore_id(store_id);
		return orders;
	}


	@Override
	public Store searchStoreById(int id) {
		
		return new StoreDaoImpl().searchStoreById(id);
	}


	@Override
	public List<Order> searchMyStroreOrder(int store_id, int status_id) {
		OrderDao od=new OrderDaoImpl();
		List<Order> list=od.searchMyStoreOrderByStatus_id(store_id, status_id);
		return list;
	}

	@Override
	public List<Goods> searchMyGoodsByName(int store_id,String str) {
		GoodsDao gd=new GoodsDaoImpl();
		List<Goods> goods=new ArrayList<>();
		
		 goods=gd.searchMyByDesc(store_id, str);
		
		return goods;
	}

	@Override
	public Goods searchStoreGoodsById(int id) {
		return new GoodsDaoImpl().searchStoreGoodsByGood_id(id);
	}

	@Override
	public int searchIdByGood(Goods good) {
		GoodsDao gd=new GoodsDaoImpl();
		return gd.searchGood_idByGood(good);
	}

	//给申请注销用的
	@Override
	public boolean  editStore_statusByStore_id(int store_id) {
		String sql="update store set status = 4 where  id = ?";
		Object[] params=new Object[]{store_id};
		int result=UpdateDao.update(sql, params);
		return result>0?true:false;
	}

	@Override
	public Goods searchGoodsByGoodId(int id) {
		return new GoodsDaoImpl().searchGoodsByGoodId(id);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return new GoodsDaoImpl().count();
	}


}
