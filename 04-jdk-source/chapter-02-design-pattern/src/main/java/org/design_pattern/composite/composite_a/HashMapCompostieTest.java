package org.design_pattern.composite.composite_a;

import java.util.HashMap;

/**
 * @author lingwh
 * @desc HashMap中的组合模式
 * @date 2026/7/9 00:00
 */
public class HashMapCompostieTest {
    public static void main(String[] args) {
        HashMap<String, String> sons = new HashMap<>();
        sons.put("儿子", "张三");
        System.out.println(sons);
        HashMap<String, String> daghters = new HashMap<>();
        daghters.put("女儿", "小红");
        System.out.println(daghters);
        HashMap<String, String> parent = new HashMap<>();
        parent.putAll(sons);
        parent.putAll(daghters);
        System.out.println(parent);
    }
}
