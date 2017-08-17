/*package com.boco.rofh.webservice;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {

	@Autowired
	private ICrmService crmService;
	
	@Autowired
	private IPbossService pbossService;
	
	@Autowired
	private IActiveService activeService;
	
	@Autowired
	private Bus bus;
	
	@Bean
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }
	
	@Bean
    public Endpoint iCrm() {
        EndpointImpl endpoint = new EndpointImpl(bus,crmService);
        endpoint.publish("/ICrm");
        return endpoint;
    }
	
	@Bean
    public Endpoint iPboss() {
        EndpointImpl endpoint = new EndpointImpl(bus,pbossService);
        endpoint.publish("/IPboss");
        return endpoint;
    }
	
    @Bean
    public Endpoint iActivate() {
        EndpointImpl endpoint = new EndpointImpl(bus,activeService);
        endpoint.publish("/IActivate");
        return endpoint;
    }
}
*/