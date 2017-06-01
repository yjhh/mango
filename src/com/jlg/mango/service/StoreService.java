package com.jlg.mango.service;

import java.util.List;

import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Item;
import com.jlg.mango.entity.Order;
import com.jlg.mango.entity.Picture;
import com.jlg.mango.entity.Store;

/**
 * 商铺Service接口
 * 		商家对商铺的商品等进行操作
 * 订单状态 
	未处理 0
	正在处理 1
	已处理 2
	申请退货 3
	正在退货 4
	已退货 5
 */
public interface StoreService {

	/**增加商品(基本 信息，无图片)*/
	boolean addGoods(Goods good);
	
	/** 增加给定编号的商品的图片 */
	boolean addPictures(int goodsId, String url);
	
	/**删除商品 */
	boolean delGoods(int id);
	
	/**修改商品*/
	boolean editGoods(Goods good);
	
	/**查询本店所有商品 */
	List<Goods> searchMyStoreGoods(int store_id);
	/**
	 * 查询本店商品根据商品描述
	 */
	List<Goods> searchMyGoodsByName(int store_id,String str);
	
	/**查询本店所有订单 */
	List<Order> searchMyStoreOrders(int store_id);
	
	/**
	 * 根据商品编号查询商品的图片
	 */
	List<Picture> searchPicture(int good_id);
	
	/**查询订单  */
	
	List<Order> searchOrders(int status_id);
	
	/** 处理订单 */
	boolean updateOrders(Order order);
	
	/**
	 * 修改订单部分信息（购物者收货电话和地址）
	 */
	boolean updateOrder(Order order);
	
	
	/**
	 * 查询所有订单
	 */
	List<Order> searchOrders();
	
	/**
	 * 用户用：通过 id查询商品
	 */
	Goods searchGoodsById(int id);
	
	/**
	 * 商铺用：通过 id查询店铺
	 */
	Goods searchStoreGoodsById(int id);
	
	/**
	 * 管理员和购物车过期商品用： 根据商品编号查询商品
	 */
	Goods searchGoodsByGoodId(int id);
	
	
	/**
	 * 注销店铺（提出申请，让管理员审核）
	 */
	boolean editStoreStatus(int status,int user_id);
	
	/**
	 * 通过订单编号查询订单项
	 */
	List<Item> searchItemByOrder_id(int order_id);
	

	/** 根据商铺编号查询商铺 */
	Store searchStoreById(int id);
	

	/**
	 *根据订单查询  本店铺  的订单
	 */
	List<Order> searchMyStroreOrder(int store_id,int status_id);

	/**
	 * 根据商品信息查询id
	 */
	int searchIdByGood(Goods good);

	
	
	/**
	 * 根据店铺编号修改店铺状态
	 */
	boolean  editStore_statusByStore_id(int store_id);

	int count();
}
