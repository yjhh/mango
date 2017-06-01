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

//查询本店所有商品，session提供店铺id
@WebServlet("/store_goodsajax")
public class store_allGoods  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		StoreService stsc=new StoreServiceImple();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		List<Goods> goods=new ArrayList<>();
		
		int pageSize=5;
		
		int store_id=(int)session.getAttribute("store_id");

		int currentPage=Integer.parseInt(request.getParameter("p"));
		//搜索内容`
		String str=request.getParameter("desc");
	
		if(str.equals("a")){
			goods=stsc.searchMyStoreGoods(store_id);
		}
		else{
			goods=stsc.searchMyGoodsByName(store_id, str);
		}
		
		
		for(int i=0;i<goods.size();i++){
			List<Picture> pics=new ArrayList<>();
			pics=stsc.searchPicture(goods.get(i).getId());
			goods.get(i).setList(pics);
		}
		
		GeneralService gs=new GeneralServiceImpl();
		
		PageBean<Goods> pgoods=gs.getPageList(goods, currentPage, pageSize);
		JSONObject json=new JSONObject(pgoods);
		out.print(json);
		out.flush();out.close();
	}
}
