/**
 * @Project: main-framework
 * @File: HtmlUtil.java
 * @package onekr.framework.utils
 * @Description:
 * @author micwing
 * @date 2013-8-19 上午9:00:47
 * @version V1.0
 *
 * Copyright (c) 2013 BGoal Soft Studio. All Rights Reserved.
 *
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent or the
 * registration of a utility model, design or code.
 */
package onekr.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: HtmlUtil
 * @Description:
 * @author micwing
 * @date 2013-8-19 上午9:00:47
 */
public class HtmlUtil {
	public static String delHTMLTag(String htmlStr) {
		if (htmlStr == null || "".equals(htmlStr.trim()))
			return "";
		
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}
	
	public static String cvtHtmlTxt(String content) {
		if (content == null)
			return "";
		String html = content;

		// html = html.replace( "'", "&apos;");
		html = html.replaceAll("&", "&amp;");
		html = html.replace("\"", "&quot;"); // "
		html = html.replace("\t", "&nbsp;&nbsp;");// 替换跳格
		html = html.replace(" ", "&nbsp;");// 替换空格
		html = html.replace("<", "&lt;");

		html = html.replaceAll(">", "&gt;");

		return html;
	}

	public static void main(String[] args) {
		String str = cvtHtmlTxt("<p>   <span><span>学员：顾侃 <br /></span>教练时间：2012年8月21日下午13：00-14：00</span></p><p> <span>教练：高荣湘</span></p><p>  <span>话题：<span><strong>如何提升自己和身边人的工作积极性？</strong></span></span></p><p>  <span><span><span>主要收获：</span></span></span></p><p> <span><span><span>有幸参与企业教练计划，我作为一个学员与高教练进行了为时一个多小时的愉快交流。<span> <br />首先，提升工作积极性，其目标是双赢的。员工积极的工作能够为企业创造更多的财富，而同时员工必定能获得应有的回报。<span></span></span></span></span></span></p><div>    <span>其次，达到积极性提升的过程是多样的因为员工的诉求是多变的。承诺奖金、给予成就感、进行考核等等方式方法对于不同的个体其作用也是有差异的。</span><span><br />此外，积极性的提高有着许多必不可少的条件因素，其中最重要的一点就是榜样的力量或者说领导力。任何达成理想的愿望都离不开实行行动，任何提高他人积极性的目标都离不开以身作则。具备这些条件才是拥有领导力的关键，而领导力正是提升员工积极性的核心。</span></div><p>   <br /></p><div>  <span> 一个小时很短暂，但学到东西却不少，希望这样的机会能更多一些。</span></div><p>    </p><p>    <span><br /></span></p>");
		System.out.println(str);
	}
}
