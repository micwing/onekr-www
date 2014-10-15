/**
 * @Project: main-framework
 * @File: Test.java
 * @package onekr.framework.tools.freemarker
 * @Description:
 * @author micwing
 * @date 2013-6-25 下午5:53:38
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
package onekr.framework.tools.freemarker;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Template;

/** 
 * @ClassName: Test 
 * @Description: 
 * @author micwing
 * @date 2013-6-25 下午5:53:38 
 */
public class Test {
    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        AppFreeMarker obj = new AppFreeMarker();
        obj.init();
        //
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("id", "1");
        dataMap.put("name", "张三");
        dataMap.put("score", 95);
        list.add(dataMap);
        dataMap = new HashMap<String, Object>();
        dataMap.put("id", "2");
        dataMap.put("name", "李四");
        dataMap.put("score", 88);
        list.add(dataMap);
        dataMap = new HashMap<String, Object>();
        dataMap.put("id", "3");
        dataMap.put("name", "王五");
        dataMap.put("score", 72);
        list.add(dataMap);
        _Entity est = new _Entity();
        est.setId(99);
        est.setName("dingzh@zbiti.com");
        //
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("listDatas", list);
        root.put("est", est);
        root.put("message", "My first test freemarker...");
        //
        Template t = obj.getCfg().getTemplate("TestFreeMarker.ftl");
        Writer out = new OutputStreamWriter(new FileOutputStream("TestFreeMarker.html"), "GBK");
        t.process(root, out);
        out.flush();
        out.close();
        System.out.println("Successfull................");
    }
    
}

class _Entity {
	private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
