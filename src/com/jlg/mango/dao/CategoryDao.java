package com.jlg.mango.dao;

import java.util.List;

import com.jlg.mango.entity.Category;
import com.jlg.mango.entity.Goods;

/**
 * 类别Dao接口
 * 
 * @author zheng
 * 2016年10月9日-2016-下午7:45:38
 */
public interface CategoryDao {

	/** 查询所有类别 */
	List<Category> searchAllCategory();
	
	/** 根据父类编号查询所属子类 */
	List<Category> searchCategoryByParent_id(int parent_id);
	
	/** 根据编号查询类别 */
	List<Category> searchCategoryByid(int id);
	
	/** 根据父类编号查询所属子类编号 */
	List<Integer> searchCategoryByParentId(int parent_id);
	
	//根据类别名字查询商品
	List<Goods> selectByCategory(String categroyName);
	
}
