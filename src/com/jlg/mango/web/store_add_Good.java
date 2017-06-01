package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.jlg.mango.entity.Goods;
import com.jlg.mango.service.StoreService;
import com.jlg.mango.service.impl.StoreServiceImple;
import com.jlg.mango.utils.Tools;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;

@WebServlet("/store_addgoodajax")
public class store_add_Good extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		HttpSession session=request.getSession();
		
		String name=request.getParameter("name");
		double price=Double.parseDouble(request.getParameter("price"));
		
		int stock=Integer.parseInt(request.getParameter("stock"));
		
		String describe =request.getParameter("describe");
		//上架时间
		java.util.Date date=new java.util.Date();
		Date time_on_shelves=new Tools().String_To_SqlDate(date);
		String tmp = request.getParameter("s_id");
		int store_id=Integer.parseInt(tmp.trim());
		int category_id=Integer.parseInt(request.getParameter("category_id"));
		Goods goods=new Goods(name, price, stock, describe, time_on_shelves, store_id, category_id);
		StoreService stsc=new StoreServiceImple();
		boolean b=stsc.addGoods(goods);
		int id=stsc.searchIdByGood(goods);
		session.setAttribute("gid", id);
		
		pw.print(b);
		pw.flush();
		pw.close();
		
	}
	
}
