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
import org.json.JSONObject;

import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Picture;
import com.jlg.mango.entity.Store;
import com.jlg.mango.service.StoreService;
import com.jlg.mango.service.impl.StoreServiceImple;

/**
 * 商品详情Servlet处理
 */
@WebServlet("/goodsInfo.php")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//实例化服务
		StoreService storeService = new StoreServiceImple();
		int goods_id = 0;
		//获取ajax传过来的商品编号
		try {
			goods_id = Integer.parseInt(request.getParameter("goods_id"));
		} catch (Exception e) {
			response.sendError(404);
		}
		
		//查询商品
		Goods goods = storeService.searchGoodsById(goods_id);
		//查找商品图片
		List<Picture> pictures = storeService.searchPicture(goods.getId());
		goods.setList(pictures);
		
		//根据商品中的商铺编号，查询商铺(名称等信息)
		Store store = storeService.searchStoreById(goods.getStore_id());
		
		/*
		
		List list = new ArrayList<>();
		list.add(goods);
		list.add(store);
		
		//组装json
		JSONArray total = new JSONArray(list);
		
		out.println(total);
		*/
		
		/*
		JSONObject job = new JSONObject();
		job.put("goods", goods);
		job.put("store", store);
		*/
		
		
		JSONObject test1 = new JSONObject(goods);
		JSONObject test2 = new JSONObject(store);
		
		JSONObject test3 = new JSONObject();
		test3.put("goods", test1);
		test3.put("store", test2);
		
		out.println(test3);
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}

}
