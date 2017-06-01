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

import com.jlg.mango.entity.Item;
import com.jlg.mango.entity.Order;
import com.jlg.mango.entity.User;
import com.jlg.mango.service.UserService;
import com.jlg.mango.service.impl.UserServiceImpl;
import com.jlg.mango.utils.Tools;

/**
 * d订单处理Servlet
 */
@WebServlet("/OrderServlet.php")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");	
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		//需要判断session么？ 如果有过滤器可以过滤，但是没有就没办法了，我觉得需要自己过滤一下
		User user = (User)session.getAttribute("User");
		
		//从请求中获取用户编号
		String temp = request.getParameter("user_id");
		if(temp == null || "".equals(temp)){   //加上user非空
			//说明用户没有登录 重定向到提示先登录页面
			response.sendRedirect("loginFirst.html");
			return;
		}
		int user_id = Integer.parseInt(temp);
		//服务接口
		UserService us = new UserServiceImpl();
		
		//取得ajax传递过来的值
		String tmp = request.getParameter("order");
		
		try {
			JSONArray array = new JSONArray(tmp);
			for(int i = 0; i < array.length(); i++){
				JSONObject obj = new JSONObject(array.get(i).toString());
				
				//实例化一个订单实体类
				Order order = new Order();
				
				//随机生成一个订单编号   -- 到数据库查重
				int random_id = Tools.getRandomOrderId();
				while(us.searchOrderById(random_id) != null){
					random_id = Tools.getRandomOrderId();
				}
				
				order.setId(random_id);
				order.setOrderTime(new Tools().String_To_SqlDate(new java.util.Date()));
				order.setUser_id(user_id);
				
				List<Item> list = new ArrayList<>();// 购物项
				
				/** 可以直接用JSON对象的 getXXX("字段名") 的方法直接转换为你想要的数据  --- 前提是你知道你的数据格式 */
				
				//取到值
				if(!obj.isNull("store_id") && !obj.isNull("totalPrice") && !obj.isNull("receive_name") 
						&& !obj.isNull("full_address") && !obj.isNull("mobile")){
					
					order.setStore_id(obj.getInt("store_id"));    /** 这么多转换，可以写一个自定义异常 */
					order.setTotalPrice(obj.getDouble("totalPrice"));  // org.json.JSONException: JSONObject["totalPrice"] not a string.
					order.setReceive_name(obj.getString("receive_name"));
					order.setFull_address(obj.getString("full_address"));
					order.setMobile(obj.getString("mobile"));
				}else{
					out.print(false);
					return;
				}
				
				//goods字段解析
				//从当前的JSON对象中获取JSON数组
				JSONArray array1 = obj.getJSONArray("goods");
				
				//变了goods里面的每个购物项
				for(int j = 0; j < array1.length(); j++){
					
					JSONObject obj1 = new JSONObject(array1.get(i).toString());
					
					//实例化一个购物项
					Item item = new Item();
					
					if(!obj1.isNull("goods_id") && !obj1.isNull("goods_price") && !obj1.isNull("goods_num")){
						item.setGood_id(obj1.getInt("goods_id"));
						item.setGoods_price(obj1.getDouble("goods_price"));			
						item.setGoods_num(obj1.getInt("goods_num"));
						item.setGood_name("假的，有商品id，就不查了");
					}else{
						out.print(false);
						return;
					}
					//加入集合
					list.add(item);
				}
				
				//分装到order
				order.setList(list);
				//调用服务
				boolean a = us.new_Order(order);
				
				//有一个失败，就返回给ajax   --- 这边是不是可以使用事物。
				if(!a){
					out.print(a);
					return;
				}
				
			}
			
			out.print(true);
			
		} catch (Exception e) {
			out.print(false);
		}finally {
			out.flush();
			out.close();
		}
		
	}

}
