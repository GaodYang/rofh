package com.boco.rofh.webservice.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.boco.rofh.utils.XStreamUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 返回消息通用
 * @author wiggler
 *
 */
@XStreamAlias("root")
public class CommonResult<T> {

	/**
	 * 返回码
	 * 1：成功
	*	0：失败
	 */
	@XStreamAlias("result_code")
	private int resultCode;
	
	/**
	 * 如果成功则本字段可空，否则必须描述失败的原因
	 */
	@XStreamAlias("result_desc")
	private String resultDesc;
	
	/**
	 * 返回查询详细信息
	 */
	@XStreamAlias("result_content")
	private T resultContent;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public T getResultContent() {
		return resultContent;
	}

	public void setResultContent(T resultContent) {
		this.resultContent = resultContent;
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
	/**
	 * 转为xml
	 * @param cls 泛型的class 
	 * @return
	 */
	public String toXml(){
		 
		T content = this.getResultContent();
		return XStreamUtil.toXml(this,content == null ? Object.class : content.getClass());
	}
}
