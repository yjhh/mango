package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.jlg.mango.dao.impl.UserDAOImple;
import com.jlg.mango.entity.User;

/**
 * Servlet implementation class userInfoServlet
 */
@WebServlet("/userInfo")
public class userInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User us = (User) session.getAttribute("User");
		//那个3是user_id
		System.out.println(us);
		User user = new UserDAOImple().searchUserById(us.getId());
		
		session.setAttribute("User", user);
		
		JSONObject jsonObject = new JSONObject(user);
		out.print(jsonObject);
		out.flush();
		out.close();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String loginName = request.getParameter("loginName");
		String sex = request.getParameter("sex");
		String bornAdress = request.getParameter("bornAdress");
		String info = request.getParameter("userInfo");
		String userPhone = request.getParameter("phone");
		User user = (User) session.getAttribute("User");
		
		user.setLoginName(loginName);
		user.setSex(sex);
		user.setAddress(bornAdress);
		user.setInfo(info);
		user.setTelNum(userPhone);
		//那个3是user_id
		int flat = new UserDAOImple().changeUserInfo(loginName, sex,loginName, bornAdress, info,userPhone, user.getId());
		if (flat>0) {
			session.setAttribute("User", user);
			response.sendRedirect("userInfo.jsp?n=1");
			return;
		}
		
	}

}
