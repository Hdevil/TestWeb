package com.jz.util;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 * @author 乾隆 2016-9-6
 *
 */
public class DateUtil {

	/**
	 * 将字符串日期转换Date类型的日期
	 * @param str
	 * @return
	 */
	public static Date toDate(String str){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date =sdf.parse(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}
	
	/**
	 * 
	 * @param str 字符串日期
	 * @param format 日期格式
	 * @return
	 */
	public static Date toDate(String str,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date date=null;
		try {
			date =sdf.parse(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}
	
	/**
	 * 将日期类型转换为字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(Date date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		
		return sdf.format(date);
		
	}
}

