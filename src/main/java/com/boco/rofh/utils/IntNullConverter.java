package com.boco.rofh.utils;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.converters.basic.IntConverter;

public class IntNullConverter extends IntConverter{

	private int defaultValue; 
	
	public IntNullConverter(int val) {
		
		defaultValue = val;
	}
	
	@Override
	public Object fromString(String str) {
		
		if(StringUtils.isEmpty(str)){
			
			return defaultValue;
		}
		return super.fromString(str);
	}
}
