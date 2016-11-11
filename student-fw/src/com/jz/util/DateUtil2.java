package com.jz.util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil2 {

	public static Date toDate(String str){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		try {
			date =  sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	public static String getcrtime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		String time=sdf.format(nowTime);
		
		return time;
		
		
	}
	
	public static String toString(Timestamp date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		
		return sdf.format(date);
		
	}
	
	public static void main(String[] args) {
		
		System.out.println(DateUtil2.toDate("2016-12-05 12:20:20"));
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		//System.out.println(DateUtil2.toString(nowTime, "yyyy-MM-dd HH:mm:ss"));
	}
}
