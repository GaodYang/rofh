package com.boco.rofh.utils;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class IDUtils implements Configurable,IdentifierGenerator{

	
	private String tableName ;
	private String targetName ;
	
	private String createCuid(String tablename) {
		String cuid = UUID.randomUUID().toString().replace("-", "").replace("_", "").toLowerCase();
		cuid = tablename.toUpperCase() + "-" + cuid;
		return cuid;
	}

	public static String getObjectIdSql(String tablename) {
		return " get_object_id('" + tablename + "') ";
	}

	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
		
		String  cuid = (String)RofhUtil.getFieldValue(targetName, arg1);
		if(StringUtils.isNotBlank(cuid)){
			
			return cuid;
		}
		return createCuid(tableName);
	}

	@Override
	public void configure(Type arg0, Properties arg1, ServiceRegistry arg2) throws MappingException {
		
		tableName = (String) arg1.get("tableName");	
		targetName = (String) arg1.get("target_column");
	}
	
}
