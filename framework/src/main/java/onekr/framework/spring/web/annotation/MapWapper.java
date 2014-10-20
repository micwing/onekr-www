/**
 * @Project: main-framework
 * @File: MapWapper.java
 * @package onekr.framework.springmvc.annotation
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
package onekr.framework.spring.web.annotation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** 
 * @ClassName: MapWapper 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:31:07 
 */ 
public class MapWapper<K, V> {
    
    private Map<K, V> innerMap = new HashMap<K, V>();
    
    public void setInnerMap(Map<K, V> innerMap) {
        this.innerMap = innerMap;
    }
    
    public Map<K, V> getInnerMap() {
        return innerMap;
    }

    public void clear() {
        innerMap.clear();
    }

    public boolean containsKey(Object key) {
        return innerMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return innerMap.containsValue(value);
    }

    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return innerMap.entrySet();
    }

    public boolean equals(Object o) {
        return innerMap.equals(o);
    }

    public V get(Object key) {
        return innerMap.get(key);
    }

    public int hashCode() {
        return innerMap.hashCode();
    }

    public boolean isEmpty() {
        return innerMap.isEmpty();
    }

    public Set<K> keySet() {
        return innerMap.keySet();
    }

    public V put(K key, V value) {
        return innerMap.put(key, value);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        innerMap.putAll(m);
    }

    public V remove(Object key) {
        return innerMap.remove(key);
    }

    public int size() {
        return innerMap.size();
    }

    public Collection<V> values() {
        return innerMap.values();
    }
    
    @Override
    public String toString() {
        return innerMap.toString();
    }

}
