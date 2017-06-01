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
import com.jlg.mango.entity.Item;
import com.jlg.mango.entity.Order;
import com.jlg.mango.entity.PageBean;
import com.jlg.mango.service.GeneralService;
import com.jlg.mango.service.StoreService;
import com.jlg.mango.service.impl.GeneralServiceImpl;
import com.jlg.mango.service.impl.StoreServiceImple;

/**
 * 查询订单（完成版）
 * @author xiaolili
 *
 */
@WebServlet("/store_orderajax")
public class store_allOrders extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		/**
		 * 实例化
		 */
		StoreService stsc=new StoreServiceImple();
		
		/**
		 * 查询
		 */
		int store_id=Integer.parseInt(request.getParameter("store_id"));
		
		
		int  currentPage=Integer.parseInt(request.getParameter("p"));
		
		int pageSize=3;
		
		List<Order> orders=new ArrayList<>();
		
		orders=stsc.searchMyStoreOrders(store_id);
	
		if(orders==null){
		}else{
			for(int i=0;i<orders.size();i++){
				List<Item> item=new ArrayList<>();
				List<Goods> goods = new ArrayList<>();
				
				item=stsc.searchItemByOrder_id(orders.get(i).getId());
				
				orders.get(i).setList(item);
				

			}
			
			GeneralService gs=new GeneralServiceImpl();
			
			PageBean<Order> porder=gs.getPageList(orders, currentPage, pageSize);
			JSONObject json=new JSONObject(porder);
			
			out.print(json);
			out.flush();
			out.close();
		}
	}
}
