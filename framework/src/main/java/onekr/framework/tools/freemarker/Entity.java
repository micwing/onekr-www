/**
 * @Project: main-framework
 * @File: Entity.java
 * @package onekr.framework.tools.freemarker
 * @Description:
 * @author micwing
 * @date 2013-6-25 下午5:54:35
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

/** 
 * @ClassName: Entity 
 * @Description: 
 * @author micwing
 * @date 2013-6-25 下午5:54:35 
 */
public class Entity {
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
