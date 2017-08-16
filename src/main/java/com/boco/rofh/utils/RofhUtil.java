package com.boco.rofh.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

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
	
	public static String replaceBlank(String str) {
		
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
}
