package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jlg.mango.entity.Cart;
import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Picture;
import com.jlg.mango.entity.ReceiveInfo;
import com.jlg.mango.entity.Store;
import com.jlg.mango.entity.User;
import com.jlg.mango.service.StoreService;
import com.jlg.mango.service.UserService;
import com.jlg.mango.service.impl.StoreServiceImple;
import com.jlg.mango.service.impl.UserServiceImpl;

/**
 * 购物车处理类
 */
@WebServlet("/CartServlet.php")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");	
		
		HttpSession session = request.getSession();
		
		//从session中获取当前用户
		User user = (User)session.getAttribute("User");
		
		/**
		 * 测试账号
		 */
		/*user = new UserServiceImpl().login("小明", "123456");
		session.setAttribute("User", user);
*/
		if(user == null){
			//说明用户没有登录 重定向到提示先登录页面
			response.sendRedirect("loginFirst.html");
			return;
		}
		
		//请求转发
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");	
		PrintWriter out = response.getWriter();
		
		String choice = request.getParameter("choice");
		//判断用户操作
		if("1".equals(choice)){   //查询该用户所有购物项
			//从请求中获取用户编号
			String tmp = request.getParameter("user_id");
			if(tmp == null || "".equals(tmp)){
				//说明用户没有登录 重定向到提示先登录页面
				response.sendRedirect("loginFirst.html");
				return;
			}
			
			int user_id = Integer.parseInt(tmp);
			//服务接口
			UserService us = new UserServiceImpl();
			StoreService ss = new StoreServiceImple();
			
			//根据当前用户编号，查询其所有购物项
			List<Cart> carts = us.searchCartsByUser_id(user_id);  //改为  ： user_id
			List<Goods> goods = new ArrayList<>();
			List<Store> stores = new ArrayList<>();
			if(carts.size() <= 0){
				return;
			}
			//分别封装购物项 、 商品、店铺
			JSONObject jb1 = null;
			JSONObject jb2 = null;
			JSONObject jb3 = null;
			
			//封装已经封装为JSON的所有信息。
			JSONObject jb = null;
			JSONArray jsonArray = new JSONArray();
			//根据购物项得到所有商品 和每个商品所属的店铺
			for(int i = 0; i < carts.size(); i++){
				//得到商品
				Goods good = ss.searchGoodsByGoodId(carts.get(i).getGoods_id()); //调用忽略库存的查询方法。
				List<Picture> pictures = ss.searchPicture(good.getId());
				good.setList(pictures);
				goods.add(good);
				
				//得到店铺
				Store store = ss.searchStoreById(carts.get(i).getStore_id());
				
				//可遍历现有的stores集合，看当前的这个store是否已经存在，如果存在就不添加了  -- 为了修改购物车同一商铺的商品不能显示在同一块里面！！！
				stores.add(store);
				
				
				jb1 = new JSONObject(carts.get(i));
				jb2 = new JSONObject(good);
				jb3 = new JSONObject(store);
				
				jb = new JSONObject();
				
				jb.put("cart", jb1);
				jb.put("goods", jb2);
				jb.put("store", jb3);
				jsonArray.put(jb);
				
			}
			
			//将数据存入请求   -- 问题：JSON数据在jsp页面无法使用？？？  迭代器？Don't know how to iterate over supplied "items" in &lt;forEach&gt;
			out.println(jsonArray);
			
		}else if("2".equals(choice)){  //根据购物项编号删除对应记录！！！ -- 删除已使用【数据库级联】
			
			//从请求中获取购物项编号
			String tmp = request.getParameter("cart_id");
			
			if(tmp == null || "".equals(tmp)){
				//说明用户没有登录 重定向到提示先登录页面
				response.sendError(404);
				return;
			}
			
			int cart_id = Integer.parseInt(tmp);
			
			//服务接口
			UserService us = new UserServiceImpl();
			
			//根据购物项编号，删除对应购物项
			boolean b = us.deleteCart(cart_id);
			out.println(b);
			
		}else if("3".equals(choice)){
			//从请求中获取用户编号
			String tmp = request.getParameter("user_id");
			
			if(tmp == null || "".equals(tmp)){
				//说明用户没有登录 重定向到提示先登录页面
				response.sendRedirect("loginFirst.html");
				return;
			}
			
			int user_id = Integer.parseInt(tmp);
			
			//服务接口
			UserService us = new UserServiceImpl();
			
			//根据当前用户编号，查询其所有收货地址
			List<ReceiveInfo> receiveInfos = us.searchInfoByUser_id(user_id);
			
			JSONArray json = new JSONArray(receiveInfos);
			
			out.println(json);
			out.flush();
			out.close();
		}else{
			return;
		}

	}

}
