package com.sz.sogain.cfpt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {
	
	public static String valueOfString(String str, int len) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len - str.length(); i++) {
			sb.append("0");
		}
		return (sb.length() == 0) ? (str) : (sb.toString() + str);
	}
	
	public static String dateFormat(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}
	
	public static String dateTimeFormat(Date date) {
		if(date==null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static Date getDateFormat(String time,String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDateFormat(Date date,String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			return df.format(date);
		}catch (Exception e) {
//			e.printStackTrace();
		}
		return null;
	}

	public static Date getDateFormat(String time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getTimeString(Calendar calendar) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(calendar.get(Calendar.YEAR)))
				.append(valueOfString(String.valueOf(calendar.get(Calendar.MONTH) + 1), 2))
				.append(valueOfString(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2))
				.append(valueOfString(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2))
				.append(valueOfString(String.valueOf(calendar.get(Calendar.MINUTE)), 2))
				.append(valueOfString(String.valueOf(calendar.get(Calendar.SECOND)), 2))
				.append(valueOfString(String.valueOf(calendar.get(Calendar.MILLISECOND)), 3));
		return sb.toString();
	}

	public static String getTimeString(String time) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(getDateFormat(time));
		return getTimeString(calendar);
	}

	public static String getTimeString() {
		Calendar calendar = new GregorianCalendar();
		return getTimeString(calendar);
	}

}