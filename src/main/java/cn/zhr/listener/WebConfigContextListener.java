package cn.zhr.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import org.apache.log4j.Logger;

/**
 * @remark 全局网站监听
 */
@SuppressWarnings("all")
public class WebConfigContextListener implements ServletContextListener, HttpSessionAttributeListener {
	private static Logger logger = Logger.getLogger(WebConfigContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		setWebConfig(context);

	}

	private void setWebConfig(ServletContext context) {
		context.setAttribute("webroot", context.getContextPath());
		logger.info("网站webapp根目录:" + context.getAttribute("webroot"));
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
	}
}
