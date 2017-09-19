package com.sxt.myclinema.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期帮助类
 * 
 * @author Ryan
 * @version 创建时间:2017年6月4日
 */
public class DateHelper {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssS");

	public static Date toDate(String dateStr) throws ParseException {
		return sdf.parse(dateStr);
	}

	public static String toDateString(Date date) {
		return sdf.format(date);
	}
	
	public static String strByNow(){
		//获取当前时间
		Date date = new Date();
		return toDateString(date);
	}
}
