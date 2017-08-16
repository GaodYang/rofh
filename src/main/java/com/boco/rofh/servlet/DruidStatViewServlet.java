package com.boco.rofh.servlet;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * StatViewServlet
 *
 */
@WebServlet(urlPatterns = "/druid/*")
public class DruidStatViewServlet extends StatViewServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}