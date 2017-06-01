package com.jlg.mango.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.jlg.mango.service.StoreService;
import com.jlg.mango.service.impl.StoreServiceImple;
import com.jlg.mango.utils.Tools;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

/**
 * 添加商品时的添加图片
 * @author xiaolili
 *
 */
@WebServlet("/addPics_ajax")
public class store_good_addPicture extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				PrintWriter out=response.getWriter();
			//实例上传组件
				SmartUpload  su = new SmartUpload();
				//初始化
				su.initialize(getServletConfig(),request,response);
				
				HttpSession session=request.getSession();
				StoreService stsc=new StoreServiceImple();
				
				int good_id=(Integer)session.getAttribute("gid");
				String filePath="";
				//获取要上传的文件夹uploads的物理路径
				String path = "D:\\Tomcat\\apache-tomcat-8.5.5\\imgs";
				
			
				boolean b=true;
				try {
					// 上传到临时文件夹
					su.upload();

					// 获取上传的文件集合对象
					Files files = su.getFiles();
					
					int count = files.getCount();// 获取上传文件的数量
					for (int i = 0; i < count; i++) {
						com.jspsmart.upload.File file = files.getFile(i);
						
						// 判断文件是否出错
						if (!file.isMissing()) {
							// 获取文件名
							String fileName = Tools.getFileName()+"."+file.getFileExt();
							
							 b=stsc.addPictures(good_id, fileName);
							// 拼接文件路径
							filePath = path + "/" + fileName;
//							System.out.println(filePath);
							// 存储
							file.saveAs(filePath);
						}
					}
					session.removeAttribute("gid");
					response.sendRedirect("store_goods.jsp");
					out.flush();
					out.close();
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
	}
}
