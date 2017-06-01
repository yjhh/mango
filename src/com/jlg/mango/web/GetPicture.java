package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.jlg.mango.dao.impl.GoodsDaoImpl;
import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Picture;
import com.jlg.mango.service.StoreService;
import com.jlg.mango.service.impl.StoreServiceImple;

/**
 * Servlet implementation class GetPicture
 */
@WebServlet("/getPicture")
public class GetPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		StoreService store = new StoreServiceImple();
		PrintWriter pw = response.getWriter();
		//商品集合
		List<Goods> listgood = new ArrayList<>();
		//产生随机商品编号
		Set<Integer> set = new HashSet<>();
		int count = store.count();
		if(count>=19){count=19;}
		List<Integer> ids = new GoodsDaoImpl().getId();
		while(set.size()<count){
			//取任意数
			int id = (int)Math.floor(Math.random()*ids.size());
			if(set.add(id)){
				Goods good = store.searchGoodsById(ids.get(id));
				if(good != null){
					listgood.add(good);
				}
			}
			
		}
		if(listgood.size() == 0){return;}
		
		for (int i = 0; i < listgood.size(); i++) {
			//查询图片
			List<Picture> list = store.searchPicture(listgood.get(i).getId());
			for (int j = 0; j < list.size(); j++) {
				list.get(j).setUrl("/imgs/"+list.get(j).getUrl());
			}
			listgood.get(i).setList(list);
		}
		JSONArray json = new JSONArray(listgood);
		
		pw.print(json);
		pw.flush();
		pw.close();
	}

}
