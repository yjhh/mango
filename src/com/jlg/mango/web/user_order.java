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

import org.json.JSONObject;

import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Item;
import com.jlg.mango.entity.Order;
import com.jlg.mango.entity.PageBean;
import com.jlg.mango.entity.User;
import com.jlg.mango.service.GeneralService;
import com.jlg.mango.service.StoreService;
import com.jlg.mango.service.UserService;
import com.jlg.mango.service.impl.GeneralServiceImpl;
import com.jlg.mango.service.impl.StoreServiceImple;
import com.jlg.mango.service.impl.UserServiceImpl;

@WebServlet("/user_order_ajax")
public class user_order extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		UserService us=new UserServiceImpl();
		GeneralService gs=new GeneralServiceImpl();
		
		StoreService stsc =new StoreServiceImple();
		//用户编号
		User uu = (User)session.getAttribute("User");
		if(uu == null){
			response.sendError(404);
			return;
		}
		
		int user_id = uu.getId();
		//验证是否为数字格式
		try {
			Integer.parseInt(request.getParameter("p"));
		} catch (Exception e) {
			response.sendError(404);
			return;
		}
		//当前页
		int currentPage=Integer.parseInt(request.getParameter("p"));
		//每页加载3条
		int pageSize=3;
		List<Order> uorder=new ArrayList<>();
		//取出全部用户的订单
		uorder=us.searchOrderByUser_id(user_id);
		if(uorder==null){
		}else{
			for(int i=0;i<uorder.size();i++){
				List<Item> item=new ArrayList<>();
				List<Goods> goods = new ArrayList<>();
				
				item=stsc.searchItemByOrder_id(uorder.get(i).getId());
				uorder.get(i).setList(item);
				}
			}
		
		//完成分页
		PageBean<Order> puorder=gs.getPageList(uorder, currentPage, pageSize);
		//把分页的结果给Ajax
		JSONObject json=new JSONObject(puorder);
		out.print(json);
		
	}
}
