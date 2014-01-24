/**
 * @Project: main-framework
 * @File: BaseContextListener.java
 * @package onekr.framework.listener
 * @Description:
 * @author micwing
 * @date 2013-3-26 下午4:52:45
 * @version V1.0
 *
 * Copyright (c) 2013 OneKr Soft Studio. All Rights Reserved.
 *
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent or the
 * registration of a utility model, design or code.
 */
package onekr.framework.javax.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/** 
 * @ClassName: BaseContextListener 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:29:14 
 */ 
public abstract class BaseContextListener implements ServletContextListener{
	/**
     * Initialize Method
     *
     * @param event Argument.
     *
     */
    public void contextInitialized(ServletContextEvent event) {
        // Initialize
        doInit(event.getServletContext());
    }

    /**
     * Register Shutdown Task (Before JVM Shutdown, do the work)
     *
     * @param work Work
     */
    protected void addShutdownWork(Thread work) {
        Runtime.getRuntime().addShutdownHook(work);
    }


    /**
     * Do Initialize
     * @param context Web Application Context.
     */
    public abstract void doInit(ServletContext context);


    /**
     * Destroy Method
     *
     * @param event Argument.
     *
     */
    public void contextDestroyed(ServletContextEvent event) {
        doDestroy(event.getServletContext());
    }

    /**
     * Destroy
     *
     * @param context Context.
     */
    public abstract void doDestroy(ServletContext context);
}
