package org.bluebridge.section_05_map;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Map集合常用方法测试
 *
 * @author lingwh
 * @date 2019/7/9 10:30
 */
public class MapTest {

    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        // 第一次存储,返回null,直接存储
        System.out.println("第一次put():" + map.put("a", "zs"));
        // 第二次存储,把原来的值替换为新值,返回以前的值
        System.out.println("第二次put():" + map.put("a", "zs1"));
        map.put("b", "ls");
        map.put("c", "ww");
        System.out.println(map);
        // clear()
        // map.clear();
        System.out.println(map);

        // containsKey()
        System.out.println(map.containsKey("a"));
        // containsValue()
        System.out.println(map.containsValue("ls"));
        // entrySet()
        Set<Map.Entry<String, String>> entries = map.entrySet();
        // isEmpty()
        System.out.println(map.isEmpty());

        System.out.println(map.get("a"));
        Set<String> strings = map.keySet();
        System.out.println("keySet():" + strings);
        Collection<String> values = map.values();
        System.out.println("values():" + values);
    }
}
