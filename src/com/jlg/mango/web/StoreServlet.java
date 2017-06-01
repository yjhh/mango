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

import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Picture;
import com.jlg.mango.entity.Store;
import com.jlg.mango.service.AdminService;
import com.jlg.mango.service.StoreService;
import com.jlg.mango.service.impl.AdminServiceImpl;
import com.jlg.mango.service.impl.StoreServiceImple;

/**
 * Servlet implementation class StoreServlet
 */
@WebServlet("/storeServlet")
public class StoreServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendError(404);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		//获取参数 用户id
		String param = request.getParameter("id");
		if(param == null || "".equals(param)){
			response.sendError(404);
		}
		
		//实例化
		StoreService store = new StoreServiceImple();
		
		try {	
					//根据用户id查询店铺
				Store st = store.searchStoreById(Integer.parseInt(param));
				System.out.println(st);
				//商品集合  根据店铺id查询商品、
				List<Goods> listgood = store.searchMyStoreGoods(st.getId());
				for (int j = 0; j < listgood.size(); j++) {
					
					//图片集合 商品id查询图片
					List<Picture> list = store.searchPicture(listgood.get(j).getId());
					
					//设置路径 更改为目录路径
					for (int i = 0; i < list.size(); i++) {
						list.get(i).setUrl("/imgs/"+list.get(i).getUrl());
					}
					//添加图片 图片集合添加进商品属性
					listgood.get(j).setList(list);
				}
				JSONArray json = new JSONArray(listgood);
				pw.print(json);
				
		} catch (Exception e) {
			// TODO: handle exception
			response.sendError(404);
		}finally {
			pw.flush();pw.close();
		}
	}

}
