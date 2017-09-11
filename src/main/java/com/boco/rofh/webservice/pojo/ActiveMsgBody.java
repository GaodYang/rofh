package com.boco.rofh.webservice.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.boco.rofh.utils.XStreamUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("msgBody")
public class ActiveMsgBody {

	private InformMsg informMsg;
	
	public static class InformMsg{
		
		private String relId;
		
		private String rtCode;
		
		private String rtMessage;

		public String getRelId() {
			return relId;
		}

		public void setRelId(String relId) {
			this.relId = relId;
		}

		public String getRtCode() {
			return rtCode;
		}

		public void setRtCode(String rtCode) {
			this.rtCode = rtCode;
		}

		public String getRtMessage() {
			return rtMessage;
		}

		public void setRtMessage(String rtMessage) {
			this.rtMessage = rtMessage;
		}
		
		
		@Override
		public String toString() {

			return ToStringBuilder.reflectionToString(this);
		}
	}

	public InformMsg getInformMsg() {
		return informMsg;
	}

	public void setInformMsg(InformMsg informMsg) {
		this.informMsg = informMsg;
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
	public static ActiveMsgBody toBean(String xml){
		

		return XStreamUtil.fromXml(ActiveMsgBody.class,xml,null,null);
	}
}
