package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jlg.mango.entity.PageBean;
import com.jlg.mango.entity.Store;
import com.jlg.mango.entity.StoreStatus;
import com.jlg.mango.service.AdminService;
import com.jlg.mango.service.impl.AdminServiceImpl;
import com.jlg.mango.service.impl.GeneralServiceImpl;

@WebServlet("/storeAjax")
public class StoreAjax extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		//获取参数
		String value = request.getParameter("value");
		String type = request.getParameter("type");
		String status = request.getParameter("status").trim();
		System.out.println("status:"+status);
		//实例化
		AdminServiceImpl admin = new AdminServiceImpl();
		int id = Integer.parseInt(value);
		boolean b = false;
		//判断要执行何种操作
		switch (type) {
		case "1":
			//删除
			b = admin.deleteStoreById(id);
			break;

		case "2":	
			//判断状态是否正常
			if(status.equals("0")){
				b = admin.updateStoreStatusById(id, 1);
			}else if(status.equals("1") || status.equals("2")){	//状态不正常，已被冻结
				b = admin.updateStoreStatusById(id, 0);
			}else if(status.equals("4")){
				b = admin.updateStoreStatusById(id, 3);
			}
			break;
		}
		pw.print(b);
		pw.flush();
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//设置编码
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			
			PrintWriter out = response.getWriter();
			//获取参数
			
			String name = request.getParameter("name").trim();
			String current = request.getParameter("current");
			
			String choice = request.getParameter("choice");
			//实例化服务
			AdminService admin = new AdminServiceImpl();
			switch (choice) {
			case "1":			//用户姓名查询
				List<Store> list = admin.searchStoreByName(name);
				PageBean<Store> page = new GeneralServiceImpl().getPageList(list, Integer.parseInt(current), 10);
				//json数组
				JSONObject json = new JSONObject(page);
				out.print(json);
				out.flush();
				out.close();
				break;
			case "2":			//店铺状态查询
				try {
					
					List<Store> liststore = null;
					if("".equals(name)){
						liststore = admin.searchStoreByName(name);
					}else{
						int status = -1;
						StoreStatus [] sta = StoreStatus.values();
						for (StoreStatus storeStatus : sta) {
							if(name.equals(storeStatus.getDesc())){
								status = storeStatus.getStatus();
								break;
							}
						}
						liststore = admin.searchStoreByStatus(status);
					}
					PageBean<Store> pageBean = new GeneralServiceImpl().getPageList(liststore, Integer.parseInt(current), 10);
					//json数组
					JSONObject jsonlist = new JSONObject(pageBean);
					out.print(jsonlist);
				} catch (Exception e) {
				}
				out.flush();
				out.close();
				break;
			}
			
			
	}

}
