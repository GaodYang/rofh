package com.boco.rofh.servlet;

import javax.servlet.annotation.WebServlet;

import org.apache.axis.transport.http.AxisServlet;

@WebServlet(
        urlPatterns =  "/services/*",
        loadOnStartup = 1,
        name = "AxisServlet"
)
public class WebServiceServlet extends AxisServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
