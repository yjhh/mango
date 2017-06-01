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

import org.json.JSONObject;

import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Order;
import com.jlg.mango.entity.PageBean;
import com.jlg.mango.entity.Store;
import com.jlg.mango.service.impl.AdminServiceImpl;
import com.jlg.mango.service.impl.GeneralServiceImpl;
import com.jlg.mango.service.impl.StoreServiceImple;

@WebServlet("/orderAjax")
public class OrderAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.sendError(404);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		//获取参数
		String current = request.getParameter("current");
		String name = request.getParameter("name");
		String choice = request.getParameter("choice");
		
		List<Order> list = new ArrayList<>();
		
		if(name=="" || name == null){
			//得到所有订单
			list=new AdminServiceImpl().searchAllOrders();
				
				//遍历订单
				for (int i = 0; i < list.size(); i++) {
					//便利订单购物项
					for(int j = 0;j < list.get(i).getList().size();j++){
						Goods good = new StoreServiceImple().searchGoodsByGoodId(list.get(i).getList().get(j).getGood_id());
						if(good == null){
							continue;
						}
						String good_name = good.getName();
						list.get(i).getList().get(j).setGood_name(good_name);
					}
				}
			
		}else{
		
		int id = 0;
		try {
			id = Integer.parseInt(name);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		switch (choice) {
		case "1":
			//根据店铺编号查询订单
			list = new AdminServiceImpl().searchOrderByStore_id(id);
			break;

		case "2":
			//根据状态查询店铺
			list = new AdminServiceImpl().searchOrderByStatus_id(id);
			break;
		}
		PageBean<Order> page = new GeneralServiceImpl().getPageList(list, Integer.parseInt(current), 10);
	}
		PageBean<Order> page = new GeneralServiceImpl().getPageList(list, Integer.parseInt(current), 10);
		JSONObject json = new JSONObject(page);
		out.print(json);
		 out.flush();
		 out.close();
		
	}

}
