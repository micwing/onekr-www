/**
 * @Project: main-framework
 * @File: PageUtil.java
 * @package onekr.framework.utils
 * @Description:
 * @author micwing
 * @date 2013-3-26 下午4:52:45
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
package onekr.framework.utils;

/** 
 * @ClassName: PageUtil 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:33:42 
 */ 
public class PageUtil {
	
	/**
	 * 获取总页数
	 * 
	 * @param totalNum
	 * @param pageSize
	 * @return
	 */
	public static int getTotalPage(int totalNum,int pageSize){
		if(totalNum==0)
			return 0;
		else if(totalNum%pageSize==0){
			return totalNum/pageSize;
		}else
			return totalNum/pageSize+1;
	}
	
	/**
	 * 首页按钮是否可见
	 * 
	 * @param totalNum
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public static boolean isViewFirstBtn(int totalNum,int pageSize,int currentPage) {
		int totalPage = getTotalPage(totalNum, pageSize);
		if (totalPage <= 1) {
			return false;
		} else {
			if (currentPage == 1) {
				return false;
			} else {
				return true;				
			}
		}
	}
	
	/**
	 * 末页按钮是否可见
	 * 
	 * @param totalNum
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public static boolean isViewLastBtn(int totalNum,int pageSize,int currentPage) {
		return !(currentPage == getTotalPage(totalNum, pageSize));
	}
	
	/**
	 * 下一页按钮是否可见
	 * 
	 * @param totalNum
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public static boolean isViewNextBtn(int totalNum,int pageSize,int currentPage) {
		return currentPage < getTotalPage(totalNum, pageSize);
	}
	
	/**
	 * 上一页按钮是否可见
	 * 
	 * @param totalNum
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public static boolean isViewPrevBtn(int totalNum,int pageSize,int currentPage) {
		return currentPage > 1;
	}
	
	/**
	 * 得到limit的起始行号
	 * 
	 * @param totalNum
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public static int getStartNum(int totalNum,int pageSize,int currentPage) {
		int totalPage = getTotalPage(totalNum,pageSize);
		if (totalPage == 0) {
			return 0;
		} else {
			return (currentPage - 1) * pageSize;
		}
	}
	
	public static int getStartNum(String totalNum, String pageSize, String currentPage) {
		return getStartNum(Integer.parseInt(totalNum), Integer.parseInt(pageSize), Integer.parseInt(currentPage));
	}
}
