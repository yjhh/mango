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

import com.jlg.mango.dao.impl.UserDAOImple;
import com.jlg.mango.entity.PageBean;
import com.jlg.mango.entity.StoreStatus;
import com.jlg.mango.entity.User;
import com.jlg.mango.entity.UserStatus;
import com.jlg.mango.service.impl.AdminServiceImpl;
import com.jlg.mango.service.impl.GeneralServiceImpl;

@WebServlet("/userajax")
public class UserAjax extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		// 获取参数
		String value = request.getParameter("value");
		String type = request.getParameter("type");
		String status = request.getParameter("status").trim();
		// 实例化
		AdminServiceImpl admin = new AdminServiceImpl();
		int id = Integer.parseInt(value);
		boolean b = false;
		// 判断要执行何种操作
		switch (type) {
		case "1":
			// 删除
			b = admin.deleteUserById(id);
			break;

		case "2":
			// 判断状态是否正常
			if (status.equals("0")) {
				b = admin.updateUserStatusById(id, 1);
			} else if (status.equals("1") || status.equals("2")) { // 状态不正常，已被冻结
				b = admin.updateUserStatusById(id, 0);
			}
			break;
		}
		pw.print(b);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 获取参数
		String name = request.getParameter("name").trim();
		System.out.println(name);
		String choice = request.getParameter("choice");
		String current = request.getParameter("current");
		AdminServiceImpl admin = new AdminServiceImpl();
		List<User> list = null;
		switch (choice) {
		case "1":
			// 用户名查询
			list = admin.searchUserByName(name);
			break;

		case "2":
			// 用户状态查询
			try {

				if ("".equals(name)) {
					list = admin.searchUserByName(name);
				} else {
					int status = -1;
					UserStatus [] sta = UserStatus.values();
					for (UserStatus storeStatus : sta) {
						if(name.equals(storeStatus.getDesc())){
							status = storeStatus.getStatus();
							break;
						}
					}
					list = admin.searchUserByStatus(status);
				}
			} catch (Exception e) {

			}
			break;
		}
		if (list == null || list.size() == 0) {
			out.print("null");
			out.flush();
			return;
		}
		PageBean<User> pagebean = new GeneralServiceImpl().getPageList(list, Integer.parseInt(current), 10);
		// json数组
		JSONObject json = new JSONObject(pagebean);
		out.print(json);
		out.flush();
		out.close();
	}

}
