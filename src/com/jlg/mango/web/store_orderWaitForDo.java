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
 * 未处理订单
 * @author xiaolili
 *
 */
@WebServlet("/order_undoajax")
public class store_orderWaitForDo extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		StoreService stsc=new StoreServiceImple();
		//状态
		int status_id=Integer.parseInt(request.getParameter("status_id"));
		//店铺编号
		int store_id=Integer.parseInt(request.getParameter("store_id"));
		//当前页
		int  currentPage=Integer.parseInt(request.getParameter("p"));
		//每页size
		int pageSize=3;
		
		List<Order> orders=new ArrayList<>();
		
		orders=stsc.searchMyStroreOrder(store_id, status_id);
		
		if(orders==null){
		}else{
			for(int i=0;i<orders.size();i++){
				List<Item> item=new ArrayList<>();
				
				item=stsc.searchItemByOrder_id(orders.get(i).getId());
				
				orders.get(i).setList(item);
				
			}
			
			GeneralService gs=new GeneralServiceImpl();
			//得到分页对象
			PageBean<Order> porder=gs.getPageList(orders, currentPage, pageSize);
			
			JSONObject  json=new JSONObject(porder);
//			System.out.print(json.toString());
			out.print(json);
		}
		out.flush();
		out.close();
	}
}
