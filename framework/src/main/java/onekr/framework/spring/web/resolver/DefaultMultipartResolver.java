package onekr.framework.spring.web.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class DefaultMultipartResolver extends CommonsMultipartResolver {
	private String fileUploadUrl;

	@Override
	public boolean isMultipart(HttpServletRequest request) {
		if ((request.getRequestURI() + "/").startsWith(fileUploadUrl + "/")) {
			return false;
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
