package onekr.framework.spring.web.resolver;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class DefaultMultipartResolver extends CommonsMultipartResolver {
	private String fileUploadUrl;

	@Override
	public boolean isMultipart(HttpServletRequest request) {
		if (!StringUtils.isEmpty(fileUploadUrl)) {
			if (fileUploadUrl.contains(",")) {
				for (String fuu : fileUploadUrl.split(",")) {
					//
					if ((request.getRequestURI() + "/").startsWith(fuu.trim() + "/")) {
						return false;
					}
				}
			} else {
				//
				if ((request.getRequestURI() + "/").startsWith(fileUploadUrl.trim() + "/")) {
					return false;
				}
			}
		}
		
		return super.isMultipart(request);
	}

	public String getFileUploadUrl() {
		return fileUploadUrl;
	}

	public void setFileUploadUrl(String fileUploadUrl) {
		this.fileUploadUrl = fileUploadUrl;
	}

}
