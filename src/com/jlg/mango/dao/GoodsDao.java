package com.jlg.mango.dao;

import java.util.List;

import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Picture;
/**
 * 商品DAO接口
 * 
 * @author Yj
 */
public interface GoodsDao {
	/**
	 * 根据店铺编号查询本店商品
	 */
	public List<Goods> searchGoodsByStoreId(int store_id);	
	/**
	 *用户用： 根据商品编号查询商品
	 */
	public Goods searchGoodsByGood_id(int good_id);
	
	
	/**
	 *商家用： 根据商品编号查询商品
	 */
	public Goods searchStoreGoodsByGood_id(int good_id);
	
	/**
	 * 管理员和购物车过期商品用： 根据商品编号查询商品
	 */
	public Goods searchGoodsByGoodId(int good_id);
	
	/**
	 * 通过商品编号查询商品图片
	 */
	public List<Picture> searchPicture(int good_id);
	
	/**
	 * 根据父类条件名称查询商品，如果只点击了父类条件
	 */
	List<Goods> searchGoodsByCategoryParentId(int parent_id);
	
	/**
	 * 如果点击了子类条件
	 */
	List<Goods> searchGoodsByCategoryId(int id);
	
	/**
	 * 根据搜索框给的字符串查询(姓名模糊查询)
	 * 
	 */
	List<Goods> searchGoodsBysentence(String str);

     /**根据商品的描述查询本店商品
      * 
      */
	List<Goods> searchMyByDesc(int store_id,String desc);
	
	/**
	 * 根据商品的信息，查询商品的id
	 */
	int searchGood_idByGood(Goods good);
	
	int count();
	
	List<Integer> getId();
}
