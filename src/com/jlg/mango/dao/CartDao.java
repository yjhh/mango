package com.jlg.mango.dao;

import java.util.List;

import com.jlg.mango.entity.Cart;


public interface CartDao {
	/** 根据用户编号查询购物车 */
	List<Cart> searchCartsByUser_id(int user_id);
	
	/** 根据用户id和商品id和店铺id -- 查询购物车中是否有这样的记录，如果有，则返回其商品数量 */
	int searchGoodsNumByUserIdAndGoodsId(int user_id, int goods_id, int store_id);
	
}
