package org.bluebridge.section_05_map;

import java.util.*;

/**
 * LinkedHashMap和HashMap的排序对比
 *
 * @author lingwh
 * @date 2019/7/9 10:30
 */
public class LinkedHashMapTest {

    private static Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
    private static Map<String, String> map = new HashMap<String, String>();

    static {
        /**
         * LinkedHashMap
         */
        linkedHashMap.put("大", "大");
        linkedHashMap.put("2", "2");
        linkedHashMap.put("3", "3");
        linkedHashMap.put("4", "4");
        linkedHashMap.put("5", "5");
        linkedHashMap.put("小", "小");
        linkedHashMap.put("7", "7");
        linkedHashMap.put("8", "8");
        linkedHashMap.put("9", "9");

        /**
         * HashMap
         */
        map.put("大", "大");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("小", "小");
        map.put("6", "6");
        map.put("7", "7");
        map.put("8", "8");
        map.put("9", "9");
    }

    public static void main(String[] args) {
        /**
         * 打印Map
         */
        printMap(linkedHashMap);
        printMap(map);

        /**
         * 测试Map集合的valuesAPI
         */
        valuesAPI(map);

        Map<Door, String> pigMap = new LinkedHashMap<>();
        pigMap.put(new Door("aa", 18), "1");
        pigMap.put(new Door("bb", 28), "2");
        pigMap.put(new Door("cc", 38), "3");
        pigMap.put(new Door("aa", 18), "4");
        System.out.println(pigMap);
    }

    /**
     * 打印Map
     *
     * @param map
     */
    public static void printMap(Map<String, String> map) {
        System.out.println("-----------------打印" + map.getClass().getSimpleName() + "开始-----------------------");
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            System.out.println(key + "----" + value);
        }

        /**
         * map的另一种遍历方式
         */
        // for (Iterator iterator = map.values().iterator(); iterator.hasNext();) {
        // String name = (String) iterator.next();
        // System.out.println(name);
        // }
        System.out.println("-----------------打印" + map.getClass().getSimpleName() + "结束-----------------------");
    }

    /**
     * Map集合的values API:获取Map中所有的值，不包括键
     *
     * @param map
     */
    public static void valuesAPI(Map<String, String> map) {
        System.out.println(
                "-----------------测试" + map.getClass().getSimpleName() + "结values()方法开始-----------------------");
        System.out.println(map);
        System.out.println(map.values());
        System.out.println(
                "-----------------测试" + map.getClass().getSimpleName() + "结values()方法结束-----------------------");
    }
}

class Door {

    private String name;
    private Integer age;

    public Door(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Door door = (Door) o;

        if (name != null ? !name.equals(door.name) : door.name != null) {
            return false;
        }
        return age != null ? age.equals(door.age) : door.age == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Door{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
