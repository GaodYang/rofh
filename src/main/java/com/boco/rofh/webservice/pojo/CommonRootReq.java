package com.boco.rofh.webservice.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.boco.rofh.utils.XStreamUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 公共的root节点
 * @author gaoyang
 *
 */
@XStreamAlias("root")
public class CommonRootReq<T> {

	/**
	 * 公共头节点
	 */
	@XStreamAlias("msg_head")
	private CommonHeadReq  msgHead;
	
	/**
	 * 消息体
	 */
	@XStreamAlias("msg_content")
	private T msgContent;

	public CommonHeadReq getMsgHead() {
		return msgHead;
	}

	public void setMsgHead(CommonHeadReq msgHead) {
		this.msgHead = msgHead;
	}

	public T getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(T msgContent) {
		this.msgContent = msgContent;
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
	/**
	 * xml解析为bean
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CommonRootReq<T> toBean(String xml,Class<T> cls){
		

		return XStreamUtil.fromXml(this.getClass(),xml,cls,"msg_content");
	}
}
