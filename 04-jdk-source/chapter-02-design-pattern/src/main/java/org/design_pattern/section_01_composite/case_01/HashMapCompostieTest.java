package org.design_pattern.section_01_composite.case_01;

import java.util.HashMap;

/**
 * HashMap中的组合模式
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
