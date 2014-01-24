/**
 * @Project: main-framework
 * @File: AppContext.java
 * @package onekr.framework.lang
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
package onekr.framework.lang;

import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: AppContext 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:29:10 
 */ 
public class AppContext {

    /** 应用上下文线程变量 */
    private static final ThreadLocal<AppContext> appContextHolder = new ThreadLocal<AppContext>();

    /** 上下文中的属性 */
    protected Map<String, Object>                properties       = new HashMap<String, Object>();

    /**
     * 获取应用上下文
     * @return appContext 应用上下文
     */
    public static AppContext get() {
        AppContext appContext = appContextHolder.get();

        if (appContext == null) {
            appContext = new AppContext();
            appContextHolder.set(appContext);
        }

        return appContext;
    }

    /**
     * 增加属性
     * @param key 属性键
     * @param value 属性值
     * @return 属性值
     */
    public Object put(String key, Object value) {
        return this.properties.put(key, value);
    }

    /**
     * 删除属性
     * @param key 属性键
     * @return 属性值
     */
    public Object remove(String key) {
        return this.properties.remove(key);
    }

    /**
     * 获取属性
     * @param key 属性键
     * @return 属性值
     */
    public Object get(String key) {
        return this.properties.get(key);
    }

    /**
     * 清除上下文中的属性
     */
    public void clear() {
        this.properties = new HashMap<String, Object>();
    }

}
