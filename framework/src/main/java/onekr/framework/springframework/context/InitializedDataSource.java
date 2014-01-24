/**
 * @Project: main-framework
 * @File: InitializedDataSource.java
 * @package onekr.framework.springmvc.init
 * @Description:
 * @author micwing
 * @date 2013-6-17 上午11:11:10
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
package onekr.framework.springframework.context;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/** 
 * @ClassName: InitializedDataSource 
 * @Description: 
 * @author micwing
 * @date 2013-6-17 上午11:11:10 
 */
public abstract class InitializedDataSource implements DataSource, InitializingBean, ResourceLoaderAware {

	public static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; \n\r";

	protected final Log logger = LogFactory.getLog(InitializedDataSource.class);

	private DataSource dataSource;

	private String scriptEncoding = "UTF-8";

	private String[] sqlScripts = ArrayUtils.EMPTY_STRING_ARRAY;

	protected ResourceLoader resourceLoader;

	public InitializedDataSource() {
		super();
	}

	public InitializedDataSource(DataSource ds) {
		this();

		this.setDataSource(ds);
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setScripts(String scripts) {
		this.sqlScripts = StringUtils.tokenizeToStringArray(scripts, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
	}

	public PrintWriter getLogWriter() throws SQLException {
		return dataSource.getLogWriter();
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return dataSource.unwrap(iface);
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		dataSource.setLogWriter(out);
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return dataSource.isWrapperFor(iface);
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		dataSource.setLoginTimeout(seconds);
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public Connection getConnection(String username, String password) throws SQLException {
		return dataSource.getConnection(username, password);
	}

	public int getLoginTimeout() throws SQLException {
		return dataSource.getLoginTimeout();
	}

	public void setScriptEncoding(String scriptEncoding) {
		this.scriptEncoding = scriptEncoding;
	}

	protected void populate() throws Exception {
		logger.info("Initializing DataSource ...");
		for (String script : this.sqlScripts) {
			logger.info("\tpopulating: [" + script + "]");
			try {
				Resource resource = this.resourceLoader.getResource(script);
				if (resource.exists()) {
					DatabasePopulator populator = this.createPopulator(resource);
					DatabasePopulatorUtils.execute(populator, dataSource);
					logger.info("\t\t[DONE].");
				} else {
					logger.warn("\t\t[NOT EXISTS].");
				}
			} catch (Exception ex) {
				logger.warn("\t\t[IGNORE] with [" + ex.getMessage() + "].");
			}
		}
		logger.info("DataSource initialized.");
	}

	protected DatabasePopulator createPopulator(Resource script) {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(false);
		populator.setIgnoreFailedDrops(false);
		populator.setScripts(new Resource[] { script });
		populator.setSqlScriptEncoding(scriptEncoding);
		return populator;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.dataSource);

		if (!ArrayUtils.isEmpty(this.sqlScripts))
			this.populate();
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
}