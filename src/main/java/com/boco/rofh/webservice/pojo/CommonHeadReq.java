package com.boco.rofh.webservice.pojo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.boco.rofh.utils.DateTimeConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * 
 * @author gaoyang
 *
 * 请求报文信息中的公共请求头
 * 
 */

public class CommonHeadReq {

	/**
	 * 发送时间
	 */
	@XStreamConverter(value=DateTimeConverter.class,strings={"yyyy-MM-dd HH:mm:ss"})
	private Date time;
	
	/**
	 * 版本信息
	 */
	private String version;
	
	
	/**
	 * 调用方识别码
	 */
	private String from;
	
	/**
	 * 接收方识别码
	 */
	private String to;
	
	/**
	 * 消息类型
	 */
	@XStreamAlias("msg_type")
	private String msgType;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
