package onekr.framework.spring.context;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class AppContextLoaderListener extends ContextLoaderListener {
	
	private static final String PROFILE_PRODUCT_FILE_DIR = "/_onekr_pro.txt";
	
	private static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
	
	@Override
	public WebApplicationContext initWebApplicationContext(
			ServletContext servletContext) {
		init(servletContext);
		return super.initWebApplicationContext(servletContext);
	}

	private void init(ServletContext context) {
		try {
			File file = new File(PROFILE_PRODUCT_FILE_DIR);
			System.setProperty(SPRING_PROFILES_ACTIVE, file.exists() ? "pro" : "dev");
		} catch (Exception e) {
			return;
		}
	}
}
