package com.jlg.mango.service.impl;

import java.util.List;

import com.jlg.mango.dao.CartDao;
import com.jlg.mango.dao.UpdateDao;
import com.jlg.mango.dao.impl.CartDAOImple;
import com.jlg.mango.dao.impl.CategoryDaoImpl;
import com.jlg.mango.dao.impl.GoodsDaoImpl;
import com.jlg.mango.dao.impl.OrderDaoImpl;
import com.jlg.mango.dao.impl.ReceiveInfoDAOImple;
import com.jlg.mango.dao.impl.StoreDaoImpl;
import com.jlg.mango.dao.impl.UserDAOImple;
import com.jlg.mango.entity.Cart;
import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Order;
import com.jlg.mango.entity.ReceiveInfo;
import com.jlg.mango.entity.Store;
import com.jlg.mango.entity.User;
import com.jlg.mango.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public List<Goods> searchGoodsByName(String goodsName) {
		
		return new GoodsDaoImpl().searchGoodsBysentence(goodsName);
	}

	@Override
	public List<Store> searchStore(String storeName) {
		
		return new StoreDaoImpl().searchStoreByName(storeName);
	}

	@Override
	public boolean resgin(User user) {
		int count = 0;
		count = new UserDAOImple().userRegister(user);
		return count>0?true:false;
	}

	@Override
	public boolean new_Order(Order order) {
		int count = 0;
		int num = 0;
		//sql语句（订单主表）
		String sql = "insert into `order`(id,orderTime,totalPrice,recive_name,full_address,mobile,user_id,"
				+ "store_id,status_id) values(?,?,?,?,?,?,?,?,?)";
		//参数数组（订单主表）
		Object[] param = new Object[]{order.getId(),order.getOrderTime(),order.getTotalPrice(),
				order.getReceive_name(),order.getFull_address(),order.getMobile(),
				order.getUser_id(),order.getStore_id(),order.getStatus_id()};
		count = UpdateDao.update(sql, param);
		
		if(count > 0){
			//sql语句（订单子表）
			String sql_item = "insert into item(order_id,goods_id,goods_price,goods_num) values(?,?,?,?)";
			for (int i = 0; i < order.getList().size(); i++) {
				Object[] params = new Object[]{order.getId(),order.getList().get(i).getGood_id(),order.getList().get(i).getGoods_price(),order.getList().get(i).getGoods_num()};
				num = UpdateDao.update(sql_item, params);
			}
		}else{
			return false;
		}
		
		return num>0?true:false;
	}

	@Override
	public boolean new_Cart(Cart cart) {
		//判断当前这个商品是否已经存在这个用户的购物车中
		CartDao cd = new CartDAOImple();
		int num = cd.searchGoodsNumByUserIdAndGoodsId(cart.getUser_id(),cart.getGoods_id(),cart.getStore_id());
		int count = 0;
		if(num == 0){  //购物车中无记录 so 插入数据
			//sql语句
			String sql = "insert into cart(user_id,goods_id,store_id"
					+ ",goods_name,goods_num) values(?,?,?,?,?)";
			//参数数组
			Object[] param = new Object[]{
					cart.getUser_id(),cart.getGoods_id(),cart.getStore_id(),cart.getGoods_name(),cart.getGoods_num()
			};
			
			count = UpdateDao.update(sql, param);
			return count>0?true:false;
			
		}else {  //购物车中已经有记录  so 更新该用户 该商品的数量 +1
			return updateCartNum(num+1, cart.getUser_id(),cart.getGoods_id(),cart.getStore_id());
		}
		
	}

	@Override
	public boolean deleteCart(int cart_id) {
		//sql语句
		String sql = "delete from cart where id=?";
		//参数数组
		Object[] param = new Object[]{cart_id};
		int count = 0;
		count = UpdateDao.update(sql, param);
		return count>0?true:false;
	}

	@Override
	public boolean updateCartNum(int num, int user_id, int goods_id, int store_id) {
	//sql语句
		String sql = "update cart set goods_num=? where user_id=? and goods_id = ? and store_id = ?";
	//参数数组
		Object[] param = new Object[]{num,user_id,goods_id,store_id};
		int count = 0;
		count = UpdateDao.update(sql, param);
		return count>0?true:false;
	}

	@Override
	public List<Goods> selectByCategory(String categroyName) {
		
		return new CategoryDaoImpl().selectByCategory(categroyName);
	}

	@Override
	public User login(String name,String password) {
		return  new UserDAOImple().userLogin(name, password);
	}

	@Override
	public List<Cart> searchCartsByUser_id(int user_id) {
		return new CartDAOImple().searchCartsByUser_id(user_id);
	}
	
	@Override
	public List<Order> searchOrderByUser_id(int User_id) {
		return new  OrderDaoImpl().searchOrder(User_id);
	}


	@Override
	public boolean editUserInfo(User user) {
		
		return new UserDAOImple().editUserInfo(user)>0?true:false;
	}


	@Override
	public boolean editOrderStatusByOrder_id(int order_id, int status_id) {
		String sql="update `order` set status_id =? where id = ?";
		Object[] param = {status_id,order_id};
		int count = 0;
		count = UpdateDao.update(sql, param);
		return count>0?true:false;
	}

	@Override
	public List<ReceiveInfo> searchInfoByUser_id(int user_id) {
		return new ReceiveInfoDAOImple().searchInfoByUser_id(user_id);
	}

	@Override
	public boolean addReceiveInfo(String name, String address, String phone, int id) {
		return new ReceiveInfoDAOImple().addReceiveInfo(name, address, phone, id) > 0 ?true:false;
	}

	@Override
	public Order searchOrderById(int order_id) {
		return new OrderDaoImpl().searchOrderByid(order_id);
	}

	@Override
	public void updateUserHead(int id, String fileName) {
		// TODO Auto-generated method stub
		String sql = "update `user` set icon =? where id = ?";
		Object[] param = new Object[]{fileName,id};
		UpdateDao.update(sql, param);
	}


}
