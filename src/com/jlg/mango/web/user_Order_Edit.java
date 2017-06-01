package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.Order;
import com.jlg.mango.service.StoreService;
import com.jlg.mango.service.UserService;
import com.jlg.mango.service.impl.StoreServiceImple;
import com.jlg.mango.service.impl.UserServiceImpl;

@WebServlet("/user_editOrder_ajax")
public class user_Order_Edit  extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * 店家用
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService us=new UserServiceImpl();
		PrintWriter out = response.getWriter();
	int order_id=Integer.parseInt(request.getParameter("order_id"));
		//状态：1就是下架商品，2就是删除商品
		int type=Integer.parseInt(request.getParameter("type"));
		boolean bz=false;
		switch(type){
		case  1:
			//type1表示支付，所以修改状态为2已支付
			bz=us.editOrderStatusByOrder_id(order_id, 2 );
			break;
		case  2:
			//type2位删除，所以修改状态为4已删除
			bz=us.editOrderStatusByOrder_id(order_id, 4 );
			break;
		case  3:
			//type3为确认收货，所以修改状态为3收货
			bz=us.editOrderStatusByOrder_id(order_id,3 );
			break;
		case  4:
			//4为已申请退货，所以修改状态为6
			bz=us.editOrderStatusByOrder_id(order_id,6);
			break;
		case  5:
			//5已发货，所以修改状态为7
			bz=us.editOrderStatusByOrder_id(order_id,7);
			break;	
		case  6:
			//6为用户申请退货，所以修改状态为5
			bz=us.editOrderStatusByOrder_id(order_id,5);
			break;	
		}
		out.print(bz);
	}
	
}
