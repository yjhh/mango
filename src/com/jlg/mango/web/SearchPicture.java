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

import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Picture;
import com.jlg.mango.service.AdminService;
import com.jlg.mango.service.StoreService;
import com.jlg.mango.service.impl.AdminServiceImpl;
import com.jlg.mango.service.impl.StoreServiceImple;

/**
 * Servlet 获取Servlet图片
 */
@WebServlet("/searchPicture.html")
public class SearchPicture extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			PrintWriter pw = response.getWriter();
			
			AdminService admin = new AdminServiceImpl();
			StoreService store = new StoreServiceImple();
			
			
			List<Goods> listgood = new ArrayList<>();
			
			
			/*得到参数*/
			String parent = request.getParameter("parent_id");
			String categroy = request.getParameter("categroy_id");
			
			if(categroy == null || categroy.equals("")){
				try {
					//根据父类id查询商品
					listgood = admin.searchGoodsByCategoryParentId(Integer.parseInt(parent));
					
					for (int i = 0;i < listgood.size();i++) {
						//根据商品编号查询图片
						List<Picture> list = store.searchPicture(listgood.get(i).getId());
						//设置路径
						for (int j = 0; j < list.size(); j++) {
							list.get(j).setUrl("/imgs/"+list.get(j).getUrl());
						}
						//添加图片到商品实例中
						listgood.get(i).setList(list);
						
					}
				} catch (Exception e) {
					// TODO: handle exception
					response.sendError(404);
				}
			}else{
				try {
					//根据类别编号查询商品
					listgood = admin.searchGoodsByCategoryId(Integer.parseInt(categroy));
					for (int i = 0;i < listgood.size();i++) {
						//根据商品编号查询图片
						List<Picture> list = store.searchPicture(listgood.get(i).getId());
						//设置路径
						for (int j = 0; j < list.size(); j++) {
							list.get(j).setUrl("/imgs/"+list.get(j).getUrl());
						}
						//添加图片到商品实例中
						listgood.get(i).setList(list);
					}
				} catch (Exception e) {
					// TODO: handle exception
					response.sendError(404);
				}
			}
			JSONArray json = new JSONArray(listgood);
			pw.print(json);
			pw.flush();pw.close();
	}

}
