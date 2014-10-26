package onekr.portalservice.article.impl;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import onekr.commonservice.model.Script;
import onekr.framework.result.Result;
import onekr.framework.utils.FileUtil;
import onekr.portalservice.article.intf.ArticleBiz;
import onekr.portalservice.model.Article;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("ArticleScriptHandler")
public class ArticleScriptHandler implements ScriptHandler,BeanClassLoaderAware {

	@Value("#{systemConfig['file.fileUploadDir']}")
	private String fileUploadDir;
	@Value("#{systemConfig['file.fileManagerUrl']}")
	private String fileManagerUrl;
	
	private ClassLoader classLoader;
	
	@Autowired
	private ArticleBiz articleBiz;
	
	@Override
	public Result handle(Script script, Object[] args) {
		List<String> urls = (List<String>) args[0];
		String author = (String) args[1];
		try {
			ProviderExpressDto expresses = getArticleExpresses(script);
			for (String url : urls) {
				Article article = executeOnceArticleScript(url, author, expresses);
				if (article != null) {					
					articleBiz.saveArticle(article);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Result result = new Result();
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	private ProviderExpressDto getArticleExpresses(Script script) throws Exception {
		String classContent = script.getContent();
		Class provider = new GroovyClassLoader(classLoader).parseClass(classContent);
		GroovyObject object = null;
		object = (GroovyObject) provider.newInstance();
		Object titleExpress = object.invokeMethod("getTitleExpress", null);
		Object contentExpress = object.invokeMethod("getContentExpress", null);
		Object summaryExpress = object.invokeMethod("getSummaryExpress", null);
		Object dateExpress = object.invokeMethod("getDateExpress", null);
		Object dateFormatExpress =  object.invokeMethod("getDateFormatExpress", null);
		summaryExpress = summaryExpress == null ? "" : summaryExpress;
		dateExpress = dateExpress == null ? "" : dateExpress;
		dateFormatExpress = dateFormatExpress == null ? "" : dateFormatExpress;
		
		ProviderExpressDto dto = new ProviderExpressDto();
		dto.setTitleExpress(titleExpress.toString());
		dto.setContentExpress(contentExpress.toString());
		dto.setSummaryExpress(summaryExpress.toString());
		dto.setDateExpress(dateExpress.toString());
		dto.setDateFormatExpress(dateFormatExpress.toString());
		return dto;
	}
	
	private Article executeOnceArticleScript(String url, String author,
			ProviderExpressDto dto) throws Exception {
	    String title = null;
	    String content = null;
	    String summary = null;
	    String imageUrl = null;
	    String date = null;
	    String dateFormat = "yyyy-MM-dd HH:mm:ss";
	    
	    Document doc = Jsoup.connect(url)
	    		.userAgent("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0")
	    		.timeout(5000).get();  
	    
	    Elements titleElements = doc.select(dto.getTitleExpress());
	    if (titleElements.isEmpty())
	    	return null;
	    Element titleElement = titleElements.get(0);
	    if (titleElement.children().size() > 0) {
	    	for (Element e : titleElement.children()) {
	    		e.remove();
	    	}
	    }
	    title = titleElement.text();
	    title = StringUtils.isEmpty(title) ? "" : title;
	    
	    Elements contentElements = doc.select(dto.getContentExpress());
	    if (contentElements.isEmpty())
	    	return null;
	    Elements contentImageElements = contentElements.select("img");
	    if (contentImageElements.size() > 0) {
	    	Iterator<Element> it  = contentImageElements.iterator();
	    	while (it.hasNext()) {
	    		reSaveStoreImage(it.next());
	    	}
	    }
	    if (contentImageElements.size() > 0) {	    	
	    	imageUrl = contentImageElements.get(0).attr("src");
	    }
	    
	    content = contentElements.html();
	    content = StringUtils.isEmpty(content) ? "" : content;
	    
	    if (!StringUtils.isEmpty(dto.getSummaryExpress())) {
	    	Elements summaryElements = doc.select(dto.getSummaryExpress());
	    	if (!summaryElements.isEmpty()) {	    		
	    		summary = summaryElements.text();
	    	}
	    }
	    String contentText = contentElements.text();
	    int contentTextLength = contentText.length();
	    summary = StringUtils.isEmpty(summary) ? contentText.substring(
	    		0, contentTextLength < 200 ? contentTextLength : 200) : summary;
	    summary = summary.length() > 200 ? summary.substring(0, 200) : summary;
	    
	    if (!StringUtils.isEmpty(dto.getDateExpress())) {
	    	if (doc.select(dto.getDateExpress()).size() == 1) {
	    		date = doc.select(dto.getDateExpress()).get(0).text();
	    	}
	    	String df = dto.getDateFormatExpress();
	    	if (!StringUtils.isEmpty(df))
	    		dateFormat = df;
	    }
	    
	    
	    Article article = new Article();
	    article.setTitle(title);
	    article.setContent(content);
	    article.setImageUrl(imageUrl);
	    article.setSummary(summary);
	    article.setAuthor(author);
	    article.setFromUrl(url);
	    article.setCreateAt(StringUtils.isEmpty(date) ? null : new SimpleDateFormat(dateFormat).parse(date));
	    return article;
	}
	
	private String reSaveStoreImage(Element element) {
		String src = element.absUrl("src");
	    File f = new File(FileUtil.getRandomFilePath(fileUploadDir, FileUtil.getPathOrUrlSuffix(src)));
	    if (!f.getParentFile().exists()) {
	    	f.getParentFile().mkdir();
	    }
	    String newSrc = null;
	    try {
			FileUtil.copyURLToFile(src, f);
			newSrc = FileUtil.cvtUrl(f, fileUploadDir, fileManagerUrl);
		} catch (Exception e) {
			newSrc = "";
		}
	    element.attr("src", newSrc);
	    return newSrc;
	}
	
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
}
