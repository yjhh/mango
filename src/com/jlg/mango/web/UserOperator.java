package com.jlg.mango.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserOperator
 */
@WebServlet("/userOperator.html")
public class UserOperator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				request.setCharacterEncoding("utf-8");
				if(request.getSession().getAttribute("User") == null){
					response.sendError(404);
				}else{
				
				request.getSession().removeAttribute("User");
				request.getSession().removeAttribute("store_id");
				response.sendRedirect("index.jsp");
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
