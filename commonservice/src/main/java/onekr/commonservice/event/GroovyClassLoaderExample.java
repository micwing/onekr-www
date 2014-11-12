//package onekr.commonservice.event;
//
//import groovy.lang.Binding;
//import groovy.lang.GroovyClassLoader;
//import groovy.lang.GroovyObject;
//import groovy.lang.GroovyShell;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//
//public class GroovyClassLoaderExample {
//	public static void main(String args[]) {
////		f1();
////		f2();
////		f3();
//		
//		String url = "http://blog.csdn.net/micwing/article/details/5261644";  
//	    long start = System.currentTimeMillis();  
//	    Document doc=null;  
//	    try{  
//	        doc = Jsoup.connect(url)
//	        		.userAgent("User-Agent: Mozilla/5.0 (Windows NT 5.2; rv:7.0.1) Gecko/20100101 Firefox/7.0.1")
//	        		.timeout(5000).get();  
//	    }  
//	    catch(Exception e){  
//	        e.printStackTrace();  
//	    }  
//	    finally{  
//	        System.out.println("Time is:"+(System.currentTimeMillis()-start) + "ms");  
//	    }  
//	    Elements elem = doc.getElementsByTag("Title");  
//	    System.out.println("Title is:" +elem.text());  
//    }
//	
//	private static void f3() {
//		String str = "@Grab(group='org.ccil.cowan.tagsoup', module='tagsoup', version='1.2' ) \r\n"
//				   +"def tagsoupParser = new org.ccil.cowan.tagsoup.Parser()  \r\n"
//				   +"def slurper = new XmlSlurper(tagsoupParser)  \r\n"  
//				   +"def htmlParser = slurper.parse(\"http://stackoverflow.com/\")  \r\n"  
//				   +"htmlParser.'**'.findAll{ it.@class == 'question-hyperlink'}.each {  \r\n"
//				   +"println it  \r\n  } ";  
//		
//		Binding binding = new Binding();
//		GroovyShell shell = new GroovyShell(binding);
//
//		Object value = shell.evaluate(str);
//	}
//	
//	/**
//	 * 在Java中动态调用运行Groovy代码
//	 */
//	private static void f1() {
//		try {
//			GroovyClassLoader loader = new GroovyClassLoader();
//			String HelloLanguage = "def hello(language) {return \"Hello $language\"}";
//			Class fileCreator = loader.parseClass(HelloLanguage);
//			GroovyObject object = (GroovyObject) fileCreator.newInstance();
//			Object val = object.invokeMethod("hello", "fdsa");
//			System.out.println(val);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 使用Shell调试脚本或表达式
//	 */
//	private static void f2() {
//		Binding binding = new Binding();
//		binding.setVariable("foo", new Integer(2));
//		GroovyShell shell = new GroovyShell(binding);
//
//		Object value = shell.evaluate("println 'Hello World!'; x = 123; return foo * 10");
//		assert value.equals(new Integer(20));
//		assert binding.getVariable("x").equals(new Integer(123));
//	}
//	
//}
