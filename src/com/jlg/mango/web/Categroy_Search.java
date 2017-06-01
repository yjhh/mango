package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.jlg.mango.entity.Category;
import com.jlg.mango.service.AdminService;
import com.jlg.mango.service.impl.AdminServiceImpl;

@WebServlet("/categroy_Search.html")
public class Categroy_Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		
		
		String parent = request.getParameter("parent_id");
		String categroy = request.getParameter("categroy_id");
		
		try{
			int parent_id = Integer.parseInt(parent);
			if(categroy != null && !categroy.equals("")){
			int categroy_id = Integer.parseInt(categroy);
//			pw.print("categroy_id"+parent_id);
			}
//			pw.print("parent_id"+parent_id);
//			response.sendRedirect("main_goods.jsp");
			request.getRequestDispatcher("main_goods.jsp").forward(request, response);
		}catch(Exception e){
			response.sendError(404);
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			PrintWriter pw = response.getWriter();
			
			//类别集合
			List<Category> list = new ArrayList<>();
			
			//获取参数
			String parent = request.getParameter("parent_id");
			String categroy = request.getParameter("categroy_id");
			
			//实例化
			AdminService admin = new AdminServiceImpl();
			
			int parent_id = 0;
			int category_id = 0;
			
				try{
					parent_id = Integer.parseInt(parent);
					list = admin.searchCategoryByParent_Id(parent_id);
				}catch (Exception e) {
					response.sendError(404);
				}
			JSONArray json = new JSONArray(list);
			pw.print(json);
			System.out.println(json);
			pw.flush();
			pw.close();
	}
}
