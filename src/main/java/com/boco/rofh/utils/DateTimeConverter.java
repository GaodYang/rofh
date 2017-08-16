package com.boco.rofh.utils;

import java.util.TimeZone;

import com.thoughtworks.xstream.converters.basic.DateConverter;

public class DateTimeConverter extends DateConverter{

	public DateTimeConverter(String format){
		
		super(format,new String[]{format},TimeZone.getDefault());
	}
}
