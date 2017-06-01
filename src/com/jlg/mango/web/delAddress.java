package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlg.mango.dao.impl.ReceiveInfoDAOImple;

/**
 * Servlet implementation class delAddress
 */
@WebServlet("/delAddress.jsp")
public class delAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String uid =request.getParameter("id");
		if (uid!=null) {
			int id = Integer.parseInt(uid);
			int flat = new ReceiveInfoDAOImple().delReceiveInfo(id);
			if (flat>0) {
				response.sendRedirect("userAddress.jsp?n=1");
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
