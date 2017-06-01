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
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.jlg.mango.dao.GoodsDao;
import com.jlg.mango.entity.Goods;
import com.jlg.mango.entity.PageBean;
import com.jlg.mango.entity.Picture;
import com.jlg.mango.service.GeneralService;
import com.jlg.mango.service.StoreService;
import com.jlg.mango.service.impl.GeneralServiceImpl;
import com.jlg.mango.service.impl.StoreServiceImple;

@WebServlet("/store_editgoodsajax")
public class store_editGoods  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session=request.getSession();
		StoreService stsc=new StoreServiceImple();
		
		PrintWriter out = response.getWriter();
		int store_id=(int) session.getAttribute("store_id");
		int goods_id=-1;
		if(request.getParameter("goods_id")!=null){
			goods_id=Integer.parseInt(request.getParameter("goods_id"));
		}
		//状态：1就是下架商品，2就是删除商品
		int type=Integer.parseInt(request.getParameter("type"));
		
		switch(type){
		case 1:
			Goods goodx=stsc.searchStoreGoodsById(goods_id);
			goodx.setStock(0);
			Boolean bx=stsc.editGoods(goodx);
			out.print(bx);
			break;
		case 2:
			Goods goods=stsc.searchStoreGoodsById(goods_id);
			goods.setStock(-1);
			Boolean bs=stsc.editGoods(goods);
			out.print(bs);
			break;
		case 3:
			boolean bz=stsc.editStore_statusByStore_id(store_id);
			out.print(bz);
			break;
		}
		out.flush();
		out.close();
	}
}
