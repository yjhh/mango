package com.jlg.mango.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlg.mango.dao.impl.StoreDaoImpl;
import com.jlg.mango.entity.User;

/**
 * Servlet implementation class reg_shop
 */
@WebServlet("/reg_shop.html")
public class Reg_shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		User user = (User)request.getSession().getAttribute("User");
		if(user == null){
			response.sendRedirect("login.jsp");
		}
		String name = request.getParameter("shop_name");
		String address = request.getParameter("shop_address");
		String email = request.getParameter("email");
		String telNum = request.getParameter("telNum");
		String describe = request.getParameter("shop_info");
		
		
		//1 是开始时候的状态  3是user_id 先写死了
		int flat = new StoreDaoImpl().addreg_shop(name, address, email, telNum, describe,2, user.getId());
		if(flat>0){
			response.sendRedirect("reg_shop.jsp?reg_shop_type=1");
		}else{
			response.sendRedirect("reg_shop.jsp?reg_shop_type=2");
		}
	}

}
