package com.jz.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class OmlineListener
 *
 */
public class OnlineListener implements HttpSessionListener {

	
	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		ServletContext context = arg0.getSession().getServletContext();
		Integer onlineNum = (Integer) context.getAttribute("onlineNum");
		if (onlineNum == null) {
			context.setAttribute("onlineNum", 1);
		} else {
			onlineNum++;
			context.setAttribute("onlineNum", onlineNum);
		}
		System.out.println("-------------"+onlineNum+"-------------");
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		ServletContext context = arg0.getSession().getServletContext();
		Integer onlineNum = (Integer) context.getAttribute("onlineNum");
		onlineNum--;
		context.setAttribute("onlineNum", onlineNum);
	}

}
