package com.sxt.myclinema.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ڰ�����
 * 
 * @author Ryan
 * @version ����ʱ��:2017��6��4��
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
		//��ȡ��ǰʱ��
		Date date = new Date();
		return toDateString(date);
	}
}
