package com.boco.rofh.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.boco.rofh.constant.WebServiceConstant;

public class RofhUtil {

	public static String getAddressDetialInfo(String str){
		
		if(StringUtils.isEmpty(str)){
			
			return "";
		}
		String s[] = str.split("\\|");
		StringBuilder sb = new StringBuilder();
		for(int i = 3 ; i<s.length ; i++){
			sb.append(s[i]);
		}
		return sb.toString();
	}
	
	/**
	 * 生成count个随机字符(a-z,0-9,字母小写)
	 */
	public static String getRandomPw(int count) {
		StringBuffer sb = new StringBuffer();
		/*
		 * 容易看不清楚的l、v、r、n字母密码生成
		 */
		String str = "0123456789abcdefghijkmopqstuwxyz";
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			int num = r.nextInt(str.length());
			sb.append(str.charAt(num));
			str = str.replace((str.charAt(num) + ""), "");
		}
		return sb.toString();
	}
	
	public static String getAccountPrefix(String type){
		
		String prefix = "";
		switch (type) {
		case WebServiceConstant.BusinessType.IPTV:
			prefix = "P";
			break;
		case WebServiceConstant.BusinessType.IMS:
			prefix = "M";
			break;
		default:
			break;
		}
		
		return prefix;
	}
	
	/**
	 * 获取某个类的某个字段的值
	 * @param fieldName 字段名称
	 * @param obj  类名称
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object getFieldValue(String fieldName,Object obj){
		
		try {
			
			Class clazz = obj.getClass();
			clazz = clazz.getSuperclass() == Object.class ? clazz : clazz.getSuperclass();
			byte[] items = fieldName.getBytes();
			items[0] = (byte) ((char) items[0] - 'a' + 'A');
			String fieldGetName = "get" + new String(items);
			Method fieldGetMet = clazz.getMethod(fieldGetName);
	        Object fieldVal = fieldGetMet.invoke(obj);
	        return fieldVal;
	        
		} catch (Exception e) {
			
			return null;
		}
		
		
	}
	
}
