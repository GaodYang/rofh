package com.boco.rofh.webservice.pojo;

public class RofhNameSpaceBean {

	private String ponwayns ;
	
	private String onuns ;
	
	private String id;


	public String getPonwayns() {
		return ponwayns;
	}

	public void setPonwayns(String ponwayns) {
		this.ponwayns = ponwayns;
	}

	public String getOnuns() {
		return onuns;
	}

	public void setOnuns(String onuns) {
		this.onuns = onuns;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	public static RofhNameSpaceBean buildBean(String id,String ponwayns,String onuns){
		
		RofhNameSpaceBean bean = new RofhNameSpaceBean();
		bean.setId(id);
		bean.setOnuns(onuns);
		bean.setPonwayns(ponwayns);
		return bean;
	}
	
}
