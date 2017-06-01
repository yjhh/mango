package com.jlg.mango.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.jlg.mango.entity.User;
import com.jlg.mango.service.impl.UserServiceImpl;
import com.jlg.mango.utils.Tools;
import com.jspsmart.upload.Request;

@WebServlet("/head.php")
@MultipartConfig
public class UpdateHead extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part part = request.getPart("head");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("User");
		PrintWriter out = response.getWriter();
		try {
			if(part != null){
				String path = "D:\\Tomcat\\apache-tomcat-8.5.5\\imgs\\user";
				String fileName = part.getSubmittedFileName();
				int split = fileName.indexOf(".");
				String old = fileName.substring(split);
				String newName = Tools.getFileName()+old;
				String filePath = path+File.separatorChar+newName;
				part.write(filePath); 
				user.setIcon(newName);
				new UserServiceImpl().updateUserHead(user.getId(),newName);
				session.setAttribute("User", user);
				out.write("true");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.write("false");
		}finally {
			out.flush();
			out.close();
		}
		
		
	}
	
}
