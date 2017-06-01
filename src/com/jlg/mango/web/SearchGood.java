package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import com.jlg.mango.service.UserService;
import com.jlg.mango.service.impl.AdminServiceImpl;
import com.jlg.mango.service.impl.StoreServiceImple;
import com.jlg.mango.service.impl.UserServiceImpl;

/**
 * Servlet implementation class SearchGood
 */
@WebServlet("/searchGood.html")
public class SearchGood extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		request.setCharacterEncoding("utf-8");
	
		request.getRequestDispatcher("search_main_goods.jsp").forward(request, response);
//		response.sendRedirect("main_goods");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			
			String type = request.getParameter("type");	//1搜商品 2搜店铺
			String name = request.getParameter("search");
//			name = URLEncoder.encode(name, "Utf-8");
			AdminService admin = new AdminServiceImpl();
			UserService user = new UserServiceImpl();
			StoreService store = new StoreServiceImple();
			
			switch (type) {
			case "1": 				//搜商品
				List<Goods>	good = user.searchGoodsByName(name);
				for (int i = 0;i < good.size();i++) {
					List<Picture> list = store.searchPicture(good.get(i).getId());
					good.get(i).setList(list);
				}
				JSONArray json = new JSONArray(good);
				pw.print(json);
				pw.flush();
				pw.close();
				return;

			case "2":				//搜店铺
				List<Store> storelist = admin.searchStoreByName(name);
					/*for (int i = storelist.size()-1; i >= 0; i--) {
						if(storelist.get(i).getStatus() > 0){
							storelist.remove(i);
						}
					}*/
					JSONArray storejson = new JSONArray(storelist);
					pw.print(storejson);
					pw.flush();
					pw.close();
				return;
			}
	}

}
