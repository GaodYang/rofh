package com.boco.rofh.webservice.task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.webservice.service.ConfigTaskReqService;


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
		
		int poolSize = WebServiceConstant.DEFAULT_POOL_SIZE;
		if(type == ConfigTaskReqService.class) {
			poolSize *=2;
		}
		ExecutorService pool = Executors.newFixedThreadPool(poolSize);
		pools.put(type, pool);
		return pool;
		
	}
	
	public void addTask(Class<?> type , Runnable runnable){
		
		ExecutorService pool = getThreadPool(type);
		pool.execute(runnable);
	}
	
	public Map<Class<?>, ExecutorService> getPools() {
		
		return pools;
	}
}
