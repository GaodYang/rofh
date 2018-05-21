package com.boco.rofh.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlRootElement(name = "result")
public class ActiveResp {
	
    private String type; 
    
    private String productId;
    
    private List<Active> activeList;
    
    @Entity
	@Table(name="NM_ACTIVE_IPTV_ALL")
    public static class Active{
    	
    	@Transient
    	private String ponPort;
    	
    	@Column(name="id")
    	@Id
    	private String password;
    	
    	@Column(name="success")
    	private String isSucess;
    	
    	@Column(name="reason")
    	private String reason;

		public String getPonPort() {
			return ponPort;
		}

		public void setPonPort(String ponPort) {
			this.ponPort = ponPort;
		}
		
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getIsSucess() {
			return isSucess;
		}

		public void setIsSucess(String isSucess) {
			this.isSucess = isSucess;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}
    	
		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@XmlElementWrapper(name="activeList") 
    @XmlElement(name="active") 
	public List<Active> getActiveList() {
		return activeList;
	}

	public void setActiveList(List<Active> activeList) {
		this.activeList = activeList;
	}
    
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
