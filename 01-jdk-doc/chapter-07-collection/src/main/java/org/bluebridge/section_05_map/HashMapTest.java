package org.bluebridge.section_05_map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * HashMap常用方法测试
 * 哈希表的作用是保证键的唯一性：hash结构底层依赖hashCode()和equals(),去重要重写hashCode()和equals()方法
 *
 * @author lingwh
 * @date 2019/7/9 10:30
 */
public class HashMapTest {

    @Test
    public void testHashMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "zs");
        map.put("b", "ls");
        map.put("c", "ww");
        map.put("d", "zl");

        // 遍历Map
        Set<String> keys = map.keySet();
        Iterator<String> iteratorKeys = keys.iterator();
        while (iteratorKeys.hasNext()) {
            System.out.println(iteratorKeys.next());
        }

        System.out.println("----------------------------------------------");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iteratorEntry = entries.iterator();
        while (iteratorEntry.hasNext()) {
            Map.Entry<String, String> entry = iteratorEntry.next();
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }

        System.out.println("----------------------------------------------");
        for (String key : keys) {
            System.out.println(key + "---" + map.get(key));
        }

        System.out.println("----------------------------------------------");
        HashMap<Pig, String> pigMap = new HashMap<>();
        pigMap.put(new Pig("aa", 18), "1");
        pigMap.put(new Pig("bb", 28), "2");
        pigMap.put(new Pig("cc", 38), "3");
        pigMap.put(new Pig("aa", 18), "4");
        System.out.println(pigMap);

        System.out.println("----------------------------------------------");
        charCounter();
    }

    /**
     * 使用HashMap统计字符出现次数
     */
    private static void charCounter() {
        HashMap<Character, Integer> counter = new HashMap<>();
        String dest = "aaccbbddac";
        for (int i = 0; i < dest.length(); i++) {
            if (!counter.containsKey(dest.charAt(i))) {
                counter.put(dest.charAt(i), 1);
            } else {
                System.out.println(dest.charAt(i));
                Integer count = counter.get(dest.charAt(i));
                counter.put(dest.charAt(i), ++count);
            }
        }
        System.out.println(counter);
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<Character, Integer>> countEntries = counter.entrySet();
        for (Map.Entry<Character, Integer> entry : countEntries) {
            builder.append(entry.getKey()).append("(").append(entry.getValue()).append(")");
        }
        System.out.println(builder);
    }
}

class Pig {

    private String name;
    private Integer age;

    public Pig(String name, Integer age) {
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

        Pig pig = (Pig) o;

        if (name != null ? !name.equals(pig.name) : pig.name != null) {
            return false;
        }
        return age != null ? age.equals(pig.age) : pig.age == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pig{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
