package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlg.mango.dao.UserDao;
import com.jlg.mango.dao.impl.UserDAOImple;
import com.jlg.mango.entity.User;
import com.jlg.mango.service.UserService;
import com.jlg.mango.service.impl.UserServiceImpl;
import com.jlg.mango.utils.Tools;

/**
 * Servlet implementation class user_reg
 */
@WebServlet("/user_reg.html")
public class user_reg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String telNum = request.getParameter("tel");
		String pwd = request.getParameter("pwd");
		User user = new User();
		
		String name = Tools.getUserName();
		user.setLoginName(name);
		user.setTelNum(telNum);
		user.setPassword(pwd);
		user.setName(name);
		boolean flat = new UserServiceImpl().resgin(user);
		if (flat) {
			response.sendRedirect("regin.jsp?n=1");
			
		}else{
			response.sendRedirect("regin.jsp?n=2");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tel = req.getParameter("phone");
		UserDao dao = new UserDAOImple();
		Boolean b = dao.isTelNumExist(tel);
		resp.getWriter().write(String.valueOf(b));
	}
	
}
