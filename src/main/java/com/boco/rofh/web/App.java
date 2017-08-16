package com.boco.rofh.web;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;

@SpringBootApplication
@EnableJpaRepositories("com.boco.rofh.dao")
@EntityScan("com.boco.rofh.entity")
@ComponentScan("com.boco.rofh")
@MapperScan("com.boco.rofh.mapper")
@ServletComponentScan("com.boco.rofh")
@EnableScheduling
public class App {

	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean 
    @ConfigurationProperties(prefix="spring.datasource")
	public DataSource druidDataSource() {
		
        return new DruidDataSource();
    }
	
	// 按照BeanId来拦截配置 用来bean的监控  
    @Bean(value = "druid-stat-interceptor")  
    public DruidStatInterceptor DruidStatInterceptor() {  
        DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();  
        return druidStatInterceptor;  
    }  
  
    @Bean  
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {  
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();  
        beanNameAutoProxyCreator.setProxyTargetClass(true);  
        // 设置要监控的bean的id  
        beanNameAutoProxyCreator.setBeanNames("*Service","*Task");  
        beanNameAutoProxyCreator.setInterceptorNames("druid-stat-interceptor");  
        return beanNameAutoProxyCreator;  
    }  
	
}
