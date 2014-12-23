package onekr.portalservice.article.impl;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import onekr.framework.result.Result;
import onekr.portalservice.model.Script;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.stereotype.Service;

@Service("UrlScriptHandler")
public class UrlScriptHandler implements ScriptHandler,BeanClassLoaderAware {

	private ClassLoader classLoader;
	
	@Override
	public Result handle(Script script, Object[] args) {
		String targetUrl = (String) args[0];
		List<String> urls = null;
		try {
			ProviderExpressDto expresses = getArticleExpresses(script);
			urls = executeScript(targetUrl, expresses);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Result result = new Result();
		result.setValue(urls);
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	private ProviderExpressDto getArticleExpresses(Script script) throws Exception {
		String classContent = script.getContent();
		Class provider = new GroovyClassLoader(classLoader).parseClass(classContent);
		GroovyObject object = null;
		object = (GroovyObject) provider.newInstance();
		Object urlExpress = object.invokeMethod("getUrlExpress", null);
		Object pageExpress = object.invokeMethod("getPageExpress", null);
		
		ProviderExpressDto dto = new ProviderExpressDto();
		dto.setUrlExpress(urlExpress.toString());
		dto.setPageExpress(pageExpress.toString());
		return dto;
	}
	
	private List<String> executeScript(String inputUrl, ProviderExpressDto dto) throws Exception {
		Set<String> targetUrls = new HashSet<String>();
		Set<String> viewedUrls = new HashSet<String>();
		List<String> result = new ArrayList<String>();
		
		targetUrls.add(inputUrl);
		
		while (viewedUrls.size() < targetUrls.size()) {
			String url = null;
			for (String u : targetUrls) {
				if (!viewedUrls.contains(u))
					url = u;
			}
			Document doc = Jsoup.connect(url)
					.userAgent("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.timeout(5000).get();
			viewedUrls.add(url);
			
			if (!StringUtils.isEmpty(dto.getPageExpress())) {
				Elements pageElements = doc.select(dto.getPageExpress());
				Iterator<Element> it  = pageElements.iterator();
		    	while (it.hasNext()) {
		    		Element e = it.next();
		    		targetUrls.add(e.absUrl("href"));
		    	}
			}
		}
		
		for (String url : targetUrls) {
			Document doc = Jsoup.connect(url)
					.userAgent("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.timeout(5000).get(); 
			
			Elements aElements = doc.select(dto.getUrlExpress());
			
			if (aElements.size() > 0) {
				Iterator<Element> it  = aElements.iterator();
				while (it.hasNext()) {
					Element e = it.next();
					result.add(e.absUrl("href"));
				}
			}
		}
	    
	    return result;
	}
	
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
}
