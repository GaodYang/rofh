package com.boco.rofh.utils;

import java.io.Writer;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.boco.rofh.constant.WebServiceConstant;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * xml工具类
 * @author gaoyang
 *
 */
public class XStreamUtil {
	
	private static final Logger logger = Logger.getLogger(XStreamUtil.class);
	
	private static Map<String,XStream> xstreamMap = new WeakHashMap<String, XStream>();

	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	/**
	 * 单例模式
	 * @throws Exception
	 */
	private XStreamUtil() throws Exception {
		
		Exception exception = new IllegalAccessException("不允许创建实例！");
		logger.error("", exception);
		throw exception;
	}
	
	/**
	 * 根据类型，获取xstream实例
	 * @param cls
	 * @return
	 */
	private static XStream getXstream(Class<?> cls1 , Class<?> cls2){
		
		XStream xstream ;
		
		String key = cls1.getName();
		if(cls2 != null){
			
			key += cls2.getSimpleName();
		}
		readWriteLock.readLock().lock();
		
		if(!xstreamMap.containsKey(key)){
		
			readWriteLock.readLock().unlock();
			
			readWriteLock.writeLock().lock();
			
			if(!xstreamMap.containsKey(key)){
			
				xstream = new XStream(new XppDriver(new NoNameCoder()) {
		
					@Override
					public HierarchicalStreamWriter createWriter(Writer out) {
						return new PrettyPrintWriter(out,"","") {
		
							// 标签的特殊字符不进行转义
							@Override
							public String encodeNode(String name) {
								return name;
							}
							
							//令其支持CDATA标签
							@Override
							protected void writeText(QuickWriter writer, String text) {
								
								if (text.startsWith(WebServiceConstant.PREFIX_CDATA) && text.endsWith(WebServiceConstant.SUFFIX_CDATA)) {  
	                                writer.write(text);  
	                            } else {  
	                                super.writeText(writer, text);  
	                            } 
							}
		
						};
					}
				});
				//处理注解
				xstream.processAnnotations(cls1);
				if(cls2 != null){
					xstream.processAnnotations(cls2);
				}
				xstream.ignoreUnknownElements();
				xstream.aliasSystemAttribute(null, "class");
				xstreamMap.put(key, xstream);
			}
			readWriteLock.readLock().lock();
			
			readWriteLock.writeLock().unlock();
		}
		
		xstream = xstreamMap.get(key);
		readWriteLock.readLock().unlock();
		return xstream;
	}
	
	/**
	 * 将对象转化为xml
	 * @param obj
	 * @return
	 */
	public static String toXml(Object obj,Class<?> cls){
		
		StringBuilder sb = new StringBuilder(WebServiceConstant.XML_HEAD);
		XStream stream = getXstream(obj.getClass(),cls);
		String xml = stream.toXML(obj);
		sb.append(xml);
		return sb.toString();
	}
	
	/**
	 * 将xml转化为对象
	 * @param <T>
	 * @param <T>
	 * @param cls
	 * @param xml
	 * @return
	 */
	public static <T> T fromXml(Class<T> cls1,String xml,@SuppressWarnings("rawtypes") Class cls2,String alias){
		
		XStream stream = getXstream(cls1,cls2);
		if(alias != null){
			stream.alias(alias, Object.class,cls2);
		}
		@SuppressWarnings("unchecked")
		T t = (T)stream.fromXML(xml);
		return t;
	}


}
