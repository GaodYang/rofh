package com.boco.rofh.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author gaoyang  2017年9月8日
 *
 * @Description: 管理激活线程
 *
 */
public enum ActiveThreadUtil {

	instance;
	
	private Map<String,Thread> threadMap = new ConcurrentHashMap<>();
	
	public void putThread(String id){
		
		threadMap.put(id, Thread.currentThread());
	}
	
	
	public void remove(String id){
		
		Thread t = threadMap.get(id);
		if(t != null){
			
			t.interrupt();
		}
		threadMap.remove(id);
	}
	
}
