package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jlg.mango.entity.Cart;
import com.jlg.mango.entity.User;
import com.jlg.mango.service.UserService;
import com.jlg.mango.service.impl.UserServiceImpl;

/**
 * 将商品添加到购物车  -- servlet处理传递过来的商品id
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		//服务接口
		UserService us = new UserServiceImpl();
		
		//获取商品id 和 商铺id
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		int store_id = Integer.parseInt(request.getParameter("store_id"));
		String goods_name = request.getParameter("goods_name");
		
		
		//获取当前session中的用户
		User user = (User)session.getAttribute("User");
		
		//判空等。。。
		if(user == null){
			response.sendRedirect("loginFirst.html");
			return;
		}
		
		
		//调用service 添加入数据库
		Cart cart = new Cart(1, user.getId(), goods_id, store_id, goods_name, 1);
		
		boolean a = us.new_Cart(cart);
		out.print(a);
		out.flush();
		out.close();
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
