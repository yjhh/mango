package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jlg.mango.dao.impl.ReceiveInfoDAOImple;
import com.jlg.mango.dao.impl.UserDAOImple;
import com.jlg.mango.entity.ReceiveInfo;
import com.jlg.mango.entity.User;

/**
 * Servlet implementation class userAddress
 */
@WebServlet("/useraddress")
public class userAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		User user = (User)request.getSession().getAttribute("User");
		//用户id先写死 用 3
		List<ReceiveInfo> infos = new ReceiveInfoDAOImple().searchInfoByUser_id(user.getId());
		JSONArray jsonArray = new JSONArray(infos);
		out.println(jsonArray);
		
		
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("conName");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		User user = (User) request.getSession().getAttribute("User");
		//那个3是user_id
		int flat = new ReceiveInfoDAOImple().addReceiveInfo(name,address,phone,user.getId());
		if (flat>0) {
			response.sendRedirect("userAddress.jsp?n=1");
		}
		
		
		
	}

}
