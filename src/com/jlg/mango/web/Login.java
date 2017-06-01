package com.jlg.mango.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jlg.mango.entity.User;
import com.jlg.mango.service.impl.UserServiceImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.html")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String check = request.getParameter("checkbox");
		HttpSession session = request.getSession();
		User user = new UserServiceImpl().login(userName, password);
		
		if(user!=null){
			if(user.getStatus() == 0){
			//添加进session
			session.setAttribute("User",user);
			
			//是否要免登录
			if(check != null){
				//设置cookie
				Cookie cookie = new Cookie("userName",userName);
				Cookie cook = new Cookie("password",password);
				cookie.setMaxAge(60*60*24*14);
				cook.setMaxAge(60*60*24*14);
				response.addCookie(cookie);
				response.addCookie(cook);
				
			}
			response.sendRedirect("index.jsp");
			}else{
				response.sendRedirect("login.jsp?n=1");
			}
		}else {
			response.sendRedirect("login.jsp?n=0");
		}
		
	}

}
