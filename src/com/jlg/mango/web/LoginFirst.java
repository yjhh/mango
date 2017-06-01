package com.jlg.mango.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlg.mango.entity.User;
import com.jlg.mango.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginFirst
 */
@WebServlet("/loginFirst.html")
public class LoginFirst extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String name = null;
		String password = null;
		
		//检查cookie
		Cookie[] cookie = request.getCookies();
		//遍历取值
		for (Cookie cook : cookie) {
			if(cook.getName().equals("userName")){
				name = cook.getValue();
			}
			if(cook.getName().equals("password")){
				password = cook.getValue();
			}
		}
		
		//判断
		if(name == null || password == null){
			response.sendRedirect("login.jsp");
		}else{
			User user = new UserServiceImpl().login(name, password);
			if(user == null){
				response.sendRedirect("login.jsp");
			}else{
				request.getSession().setAttribute("User", user);
				response.sendRedirect("index.jsp");
			}
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
