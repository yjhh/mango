package com.jlg.mango.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 工具类   --- 静态方法！
 * 
 * @author student
 */
public class Tools {
	
	public static String getUserName(){
		String usid = UUID.randomUUID().toString().split("-")[0];
		String times = new SimpleDateFormat("ssss").format(new Date());
		return "mango_"+usid+times;
	}
	
	public static String getFileName(){
		String usid = UUID.randomUUID().toString().split("-")[0];
		String times = new SimpleDateFormat("ssss").format(new Date());
		return usid+times;
	}
	
	/**
	 * String 转 util.Date
	 * @param str
	 * @return
	 */
	public java.util.Date string_to_Date(String str){
		java.util.Date date = null;
		try {
			date= new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	/**
	 * util.Date 转 sql.Date
	 * @param s
	 * @return
	 */
	public java.sql.Date util_To_SqlDate(String s){
		java.util.Date date=string_to_Date(s);
		return new  java.sql.Date(date.getTime());
	}
	
	/**
	 * String	直接to sql.Date
	 * @param date
	 * @return
	 */
	public java.sql.Date String_To_SqlDate(java.util.Date date){
		return new  java.sql.Date(date.getTime());
	}
	
	/**
	 * 生成随机订单号
	 */
	public static int getRandomOrderId(){
		
		Random random = new Random();
		
		int tmp = random.nextInt(Integer.MAX_VALUE-1000)+999;
		
		return tmp;
	}
}
