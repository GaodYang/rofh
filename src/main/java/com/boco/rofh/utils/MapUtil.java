package com.boco.rofh.utils;

import java.util.Map;

public class MapUtil {

	public static String getStringValue(@SuppressWarnings("rawtypes") Map map,String key){
		
		if(map == null){
			
			return "";
		}
		
		String value = String.valueOf(map.get(key));
		
		return value == null ? "" : value;
	}

	public static int getIntegerValue(Map<String, Object> map, String key) {
		
		if(map == null){
			
			return 0;
		}
		
		String value = map.get(key) == null ? "0" : map.get(key).toString();
		
		return Integer.valueOf(value);
	}
}
