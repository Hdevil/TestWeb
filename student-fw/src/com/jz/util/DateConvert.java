package com.jz.util;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConvert extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		String dateStr=arg1[0];
		try {
			return sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
