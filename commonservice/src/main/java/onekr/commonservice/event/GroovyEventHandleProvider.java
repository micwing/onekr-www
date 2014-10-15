//package onekr.commonservice.event;
//
//import groovy.lang.GroovyClassLoader;
//
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import onekr.commonservice.model.ArticleScript;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.BeanClassLoaderAware;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//
//public class GroovyEventHandleProvider implements EventHandleProvider, ApplicationContextAware, BeanClassLoaderAware {
//	
//	private static String[] DEFAULT_IMPORT_CLASSES = {"org.springframework.beans.factory.annotation.*", "onekr.commonservice.model.*"};
//	private static Pattern CLASS_NAME_PATTERN = Pattern.compile("([\\w\\.\\$\\*]+)");
//	private static AtomicInteger SEQ = new AtomicInteger();
//	private GroovyClassLoader classLoader;
//	private ApplicationContext applicationContext;
//
//	@Override
//	public EventHandle getEventHandle(ArticleScript script) {
//		EventHandle handle = BeanUtils.instantiateClass(getEventHandleClass(script));
//        applicationContext.getAutowireCapableBeanFactory().autowireBean(handle);
//        return handle;
//	}
//	
//	@SuppressWarnings("unchecked")
//    private Class<EventHandle> getEventHandleClass(ArticleScript script) {
//        StringBuilder sb = new StringBuilder();
//        for (String s : DEFAULT_IMPORT_CLASSES) {
//            appendClass(sb, s);
//        }
//        String s = script.getImportz();
//        if (StringUtils.isNotBlank(s)) {
//            Matcher matcher = CLASS_NAME_PATTERN.matcher(s);
//            while (matcher.find()) {
//                appendClass(sb, matcher.group(1));
//            }
//        }
//        sb.append("class ").append("EventHandle_").append("_").append(SEQ.incrementAndGet()).append(" implements EventHandle {\n");
//        sb.append(script.getContent());
//        sb.append("}\n");
//        String text = sb.toString();
//        return classLoader.parseClass(text);
//    }
//	
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext)
//			throws BeansException {
//		this.applicationContext = applicationContext;
//	}
//	
//	@Override
//	public void setBeanClassLoader(ClassLoader classLoader) {
//		this.classLoader = new GroovyClassLoader(classLoader);
//	}
//	
//	private void appendClass(StringBuilder sb, String className) {
//		sb.append("import ").append(className).append(";\n");
//	}
//}
