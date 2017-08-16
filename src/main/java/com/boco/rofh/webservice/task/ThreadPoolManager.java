package com.boco.rofh.webservice.task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.boco.rofh.constant.WebServiceConstant;


/**
 * 管理线程池
 * @author gaoyang
 *
 */
public class ThreadPoolManager {

	private ThreadPoolManager() {
	};
	
	private static final ThreadPoolManager instance = new ThreadPoolManager();
	
	public static ThreadPoolManager getInstance(){
		
		return instance;
	}
	
	
	private Map<Class<?>, ExecutorService> pools = new ConcurrentHashMap<Class<?>, ExecutorService>();
	
	
	public ExecutorService getThreadPool(Class<?> type){
		
		if(pools.containsKey(type)){
			
			return pools.get(type);
		}
		
		ExecutorService pool = Executors.newFixedThreadPool(WebServiceConstant.DEFAULT_POOL_SIZE);
		pools.put(type, pool);
		return pool;
		
	}
	
	public void addTask(Class<?> type , Runnable runnable){
		
		ExecutorService pool = getThreadPool(type);
		pool.execute(runnable);
	}
}
