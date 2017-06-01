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

@WebServlet("/order_completeajax")
public class store_order_completed  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		StoreService stsc=new StoreServiceImple();
		
		int store_id=Integer.parseInt(request.getParameter("store_id"));
		int currentPage=Integer.parseInt(request.getParameter("p"));
		int pageSize=3;
		
		List<Order> orders=new ArrayList<>();
		List<Order> result=new ArrayList<>();
		
		orders=stsc.searchMyStoreOrders(store_id);
		
		for(int i=0;i<orders.size();i++){
			if(orders.get(i).getStatus_id()==3||orders.get(i).getStatus_id()==6||orders.get(i).getStatus_id()==4){
				result.add(orders.get(i));
			}
		}
		
		if(result==null){
		}else{
			for(int i=0;i<result.size();i++){
				List<Item> item=new ArrayList<>();
				
				item=stsc.searchItemByOrder_id(orders.get(i).getId());
				
				result.get(i).setList(item);
				
			}
			
			GeneralService gs=new GeneralServiceImpl();
			
			//得到分页对象
			PageBean<Order> porder=gs.getPageList(result, currentPage, pageSize);
			JSONObject json=new JSONObject(porder);
			/*System.out.print(json.toString());*/
			out.print(json);
		}
		out.flush();
		out.close();
	}
}
