/**
 * @Project: main-framework
 * @File: Serializable.java
 * @package onekr.framework.tools
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
package onekr.framework.tools.serializer;

/** 
 * @ClassName: Serializable 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:31:46 
 */ 
public interface Serializable {
    byte[] serialize();
    void unserialize(byte[] ss);
}
