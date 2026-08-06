package org.bluebridge.section_05_jdk5.unit_02_foreach_enum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JDK1.5 增强 for 循环与枚举测试
 *
 * 1. 增强 for 循环(foreach): 简化了数组和集合的遍历, 底层使用 Iterator 实现,
 *    遍历过程中不能修改集合结构(否则会抛出 ConcurrentModificationException)
 * 2. 枚举(enum): 是一种特殊的类, 本质是 java.lang.Enum 的子类, 可以拥有属性、方法、构造器,
 *    常用于表示一组固定的常量, 枚举常量必须在最前面定义
 * 3. EnumMap/EnumSet: 专门针对枚举类型优化的高性能集合
 * 4. 枚举在 switch 中可以直接使用, 不需要加类名前缀
 * 5. 枚举实现的单例模式: 天然线程安全, 且能防止反射和序列化破坏单例
 *
 * @author lingwh
 * @date 2026/08/05 18:26
 */
public class ForEachAndEnumTest {

    /**
     * 季节枚举: 无属性的简单枚举
     */
    enum Season {
        SPRING, SUMMER, AUTUMN, WINTER
    }

    /**
     * 颜色枚举: 包含属性和构造器的枚举
     */
    enum Color {
        // 枚举常量必须在最前面定义, 后面跟构造器参数
        RED("红色", 1),
        GREEN("绿色", 2),
        BLUE("蓝色", 3);

        // 枚举可以拥有属性
        private final String description;
        private final int code;

        // 私有构造器: 枚举的构造器只能是私有的
        Color(String description, int code) {
            this.description = description;
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public int getCode() {
            return code;
        }

        /**
         * 枚举可以拥有方法
         */
        public String info() {
            return name() + "(" + code + "): " + description;
        }
    }

    /**
     * 枚举实现的单例模式: 最简洁、线程安全的单例实现
     */
    enum Singleton {
        INSTANCE;

        private int count;

        /**
         * 单例方法
         */
        public void doSomething() {
            count++;
            System.out.println("Singleton 执行第 " + count + " 次操作");
        }
    }

    /**
     * 测试增强 for 循环遍历数组
     */
    @Test
    public void testForEachArray() {
        int[] array = {1, 2, 3, 4, 5};
        int sum = 0;
        // 使用增强 for 循环遍历数组
        for (int num : array) {
            System.out.print(num + " ");
            sum += num;
        }
        System.out.println();
        System.out.println("数组元素之和: " + sum);
    }

    /**
     * 测试增强 for 循环遍历集合: List、Set、Map
     */
    @Test
    public void testForEachCollection() {
        // 遍历 List
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        System.out.print("遍历 List: ");
        for (String str : list) {
            System.out.print(str + " ");
        }
        System.out.println();
        // 遍历 Set
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
        System.out.print("遍历 Set: ");
        for (Integer num : set) {
            System.out.print(num + " ");
        }
        System.out.println();
        // 遍历 Map(遍历 entrySet)
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        System.out.print("遍历 Map: ");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();
    }

    /**
     * 测试枚举基本使用: values()、name()、ordinal()、valueOf()
     */
    @Test
    public void testEnumBasic() {
        // 遍历枚举的所有常量
        System.out.println("Season 的所有常量: ");
        for (Season season : Season.values()) {
            System.out.println("  name: " + season.name() + ", ordinal: " + season.ordinal());
        }
        // valueOf() 根据名称获取枚举常量
        Season spring = Season.valueOf("SPRING");
        System.out.println("valueOf(\"SPRING\"): " + spring);
        // 枚举常量的 toString() 返回其名称
        System.out.println("SPRING.toString(): " + Season.SPRING);
    }

    /**
     * 测试带属性和方法的枚举
     */
    @Test
    public void testEnumWithFieldAndMethod() {
        // 访问枚举的属性和方法
        System.out.println("RED 的描述: " + Color.RED.getDescription());
        System.out.println("RED 的 code: " + Color.RED.getCode());
        // 遍历枚举并调用自定义方法
        System.out.println("所有颜色信息: ");
        for (Color color : Color.values()) {
            System.out.println("  " + color.info());
        }
    }

    /**
     * 测试 EnumMap: 键为枚举类型的 Map, 性能优于 HashMap
     */
    @Test
    public void testEnumMap() {
        // 创建 EnumMap, 键的类型是枚举类型
        EnumMap<Season, String> seasonMap = new EnumMap<>(Season.class);
        seasonMap.put(Season.SPRING, "春天");
        seasonMap.put(Season.SUMMER, "夏天");
        seasonMap.put(Season.AUTUMN, "秋天");
        seasonMap.put(Season.WINTER, "冬天");
        // 遍历 EnumMap
        for (Map.Entry<Season, String> entry : seasonMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        // 获取指定键对应的值
        System.out.println("SUMMER 对应的值: " + seasonMap.get(Season.SUMMER));
    }

    /**
     * 测试 EnumSet: 专门存储枚举的 Set, 高效紧凑
     */
    @Test
    public void testEnumSet() {
        // 创建包含指定常量的 EnumSet
        EnumSet<Season> springSummer = EnumSet.of(Season.SPRING, Season.SUMMER);
        System.out.println("EnumSet.of(SPRING, SUMMER): " + springSummer);
        // 创建包含全部常量的 EnumSet
        EnumSet<Season> all = EnumSet.allOf(Season.class);
        System.out.println("EnumSet.allOf(): " + all);
        // 创建不包含任何常量的 EnumSet, 再添加元素
        EnumSet<Season> none = EnumSet.noneOf(Season.class);
        none.add(Season.WINTER);
        System.out.println("添加 WINTER 后: " + none);
        // 范围操作
        EnumSet<Season> range = EnumSet.range(Season.SPRING, Season.AUTUMN);
        System.out.println("EnumSet.range(SPRING, AUTUMN): " + range);
    }

    /**
     * 测试 switch 中使用枚举
     */
    @Test
    public void testSwitchWithEnum() {
        describeSeason(Season.SUMMER);
        describeSeason(Season.WINTER);
    }

    /**
     * 辅助方法: 使用 switch 处理枚举
     *
     * @param season 季节枚举
     */
    private void describeSeason(Season season) {
        // switch 中直接使用枚举常量名, 不需要加类名前缀
        switch (season) {
            case SPRING:
                System.out.println(season + ": 春暖花开");
                break;
            case SUMMER:
                System.out.println(season + ": 烈日炎炎");
                break;
            case AUTUMN:
                System.out.println(season + ": 秋高气爽");
                break;
            case WINTER:
                System.out.println(season + ": 寒风凛冽");
                break;
            default:
                System.out.println("未知季节");
        }
    }

    /**
     * 测试枚举实现的单例模式: 简洁、线程安全、防反射和序列化破坏
     */
    @Test
    public void testEnumSingleton() {
        // 获取单例对象, 两次获取的是同一个实例
        Singleton singleton1 = Singleton.INSTANCE;
        Singleton singleton2 = Singleton.INSTANCE;
        System.out.println("两次获取的实例是否相同: " + (singleton1 == singleton2));
        // 调用单例方法
        singleton1.doSomething();
        singleton2.doSomething();
    }
}