package com.yubin.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 并发ConcurrentHashMap测试类
 *
 * @author Administrator
 * @date 2021-06-02
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<>();
        map1.put("a", "a");
        map1.putIfAbsent("a", "b");
        Map<String, String> map2 = new Hashtable<>();
    }
}
