package onekr.framework.spring.context;

import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class AppContextLoaderListener extends ContextLoaderListener {
	
	private static final String SYSCONFIG_FILE_LOCATION = "classpath:system.properties";
	
	private static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
	
	private static final String PROPERTIES_KEY_PROFILE = "profile";
	
	@Override
	public WebApplicationContext initWebApplicationContext(
			ServletContext servletContext) {
		init(servletContext);
		return super.initWebApplicationContext(servletContext);
	}

	private void init(ServletContext context) {
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(
					new FileSystemResource(ResourceUtils.getFile(SYSCONFIG_FILE_LOCATION)));
			System.setProperty(SPRING_PROFILES_ACTIVE, properties.getProperty(PROPERTIES_KEY_PROFILE));
		} catch (Exception e) {
			return;
		}
	}
}
