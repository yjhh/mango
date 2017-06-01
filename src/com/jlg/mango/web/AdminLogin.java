package com.jlg.mango.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jlg.mango.dao.AdminDao;
import com.jlg.mango.dao.impl.AdminDaoImpl;
import com.jlg.mango.entity.Admin;
import com.jlg.mango.service.AdminService;
import com.jlg.mango.service.impl.AdminServiceImpl;



/**
 * Servlet implementation class Test
 */
@WebServlet("/adminLogin.html")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginAdmin");
		response.sendRedirect("manager.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			/*获取会话*/
			HttpSession session = request.getSession();
			session.removeAttribute("Errorlog");
			/*获取参数*/
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String checkbox = request.getParameter("checkbox");
			if(username == null || "".equals(username) || password == null || "".equals(password)){
				session.setAttribute("Errorlog", "用户名或密码不能为空");
				response.sendRedirect("adminLogin.jsp");
				return;
			}
			
			AdminService admindao = new AdminServiceImpl();
			AdminDao ad = new AdminDaoImpl();
			/*验证登录 用户名密码验证*/
			Admin admin = admindao.login(username, password);
			if(admin == null){
				//验证失败，cookie保存密码验证
				admin = ad.checkLogin(username, null);
				if(admin == null || !password.equals(admin.getPassword())){
					session.setAttribute("Errorlog", "用户名或密码错误");
					response.sendRedirect("adminLogin.jsp");
					return;
				}
			}
			
			if(admin != null){
				session.setAttribute("loginAdmin", admin);
				if(checkbox != null){
					Cookie cookieName = new Cookie("userName",URLEncoder.encode(username, "utf-8"));
					Cookie cookiePwd = new Cookie("password", admin.getPassword());
					cookieName.setMaxAge(60*60*24*7);
					cookiePwd.setMaxAge(60*60*24*7);
	//				cookieName.setMaxAge(-1);	//浏览器关闭即关闭
	//				cookiePwd.setMaxAge(-1);	//浏览器关闭即关闭
					response.addCookie(cookiePwd);
					response.addCookie(cookieName);
				}
				response.sendRedirect("manager.jsp");
				return;
			}
	}
}
