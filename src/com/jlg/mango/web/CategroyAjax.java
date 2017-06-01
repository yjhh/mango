package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.jlg.mango.entity.Category;
import com.jlg.mango.entity.PageBean;
import com.jlg.mango.service.AdminService;
import com.jlg.mango.service.impl.AdminServiceImpl;
import com.jlg.mango.service.impl.GeneralServiceImpl;

@WebServlet("/categroyAjax")
public class CategroyAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			//获取参数
			String name = request.getParameter("name");
			String current = request.getParameter("current");
			List<Category> list = null;
			AdminService admin = new AdminServiceImpl();
			//查询
			if(name.equals("") || name==null){
				list = admin.searchAllCategory();
			}else{
				try{
					int id = Integer.parseInt(name);
					list = admin.searchCategoryByParent_Id(id);
				}catch(Exception e){
					
				}
			}
			
			PageBean<Category> page = new GeneralServiceImpl().getPageList(list, Integer.parseInt(current), 10);
			
			 JSONObject json = new JSONObject(page);
			 out.print(json);
			 out.flush();
			 out.close();
	}

}
