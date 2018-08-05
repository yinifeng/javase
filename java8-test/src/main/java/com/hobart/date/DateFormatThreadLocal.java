package com.hobart.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatThreadLocal {
	public static final ThreadLocal<DateFormat> DATE_FORMAT=new ThreadLocal<DateFormat>(){
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd");
		};
	};
	
	public static Date convert(String source) throws ParseException{
		return DATE_FORMAT.get().parse(source);
	}
}
