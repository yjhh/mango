package com.jlg.mango.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jlg.mango.entity.Store;
import com.jlg.mango.entity.User;
import com.jlg.mango.service.AdminService;
import com.jlg.mango.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class Shop
 */
@WebServlet("/shop.html")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		//得到登陆用户
		User user = (User)session.getAttribute("User");
		//得到店铺id
		Object store_id = session.getAttribute("store_id");
		
		
		
		if(user == null){
			response.sendRedirect("loginFirst.html");
		}else{
			if(store_id != null){
				response.sendRedirect("store_goods.jsp");
				return;
			}
			AdminService admin = new AdminServiceImpl();
			Store store = admin.searchStoreByUserId(user.getId());
			if(store == null){
				response.sendRedirect("reg_shop.jsp");
				return;
			}else{
			session.setAttribute("store_id", store.getId());	
			session.setAttribute("status", store.getStatus());	
			response.sendRedirect("store_goods.jsp");
			return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
