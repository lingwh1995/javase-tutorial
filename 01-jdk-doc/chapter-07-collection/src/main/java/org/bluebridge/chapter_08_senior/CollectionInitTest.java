package org.bluebridge.chapter_08_senior;

import java.util.*;

/**
 * @author lingwh
 * @desc
 * @date 2026/6/12 11:12
 */
public class CollectionInitTest {

    /**
     * Java8-
     */
    // 使用匿名内部类初始化集合 - 绝对禁止 {{}}
    /**
     * 为什么绝对禁止使用 {{}} 定义并初始化集合?
     * 1. 生成额外字节码：每个 new X(){{}} 都会生成一个独立的匿名内部类文件（类名$1.class），增加应用体积和类加载时间
     * 2. 内存泄漏隐患：匿名内部类会隐式持有外部类的引用，如果这个静态常量长期存在，外部类对象永远无法被 GC 回收
     * 3. 序列化问题：匿名内部类没有自定义序列化策略，序列化 / 反序列化极易失败，且跨版本兼容性极差
     * 4. 类型判断陷阱：new ArrayList(){{}} 创建的不是 ArrayList 本身，而是它的子类，getClass() != ArrayList.class，在某些反射、序列化场景下会出现诡异 bug
     * 5. 性能开销：创建匿名子类实例比直接创建 ArrayList 慢 2-3 倍
     */
    private static final List<Integer> UNMODIFIABLE_LIST_JAVA8 = new ArrayList(){{
        add(1);
        add(2);
        add(3);
    }};

    // 使用 Collections.unmodifiableList() 初始化不可变集合 ⭐⭐⭐⭐⭐
    private static final List<Integer> UNMODIFIABLE_LIST_WITH_NULL_JAVA8 = Collections.unmodifiableList(
            Arrays.asList(1, null, 3)
    );

    // 使用静态初始化块初始化可变集合 ⭐⭐⭐⭐⭐
    private static final List<Integer> MUTABLE_LIST;
    static {
        MUTABLE_LIST = new ArrayList<>();
        MUTABLE_LIST.add(1);
        MUTABLE_LIST.add(2);
        MUTABLE_LIST.add(3);
    }

    // 使用静态初始化块初始化不可变集合 ⭐⭐⭐⭐⭐
    private static final Set<Integer> UNMODIFIABLE_SET_JAVA8;
    static {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        UNMODIFIABLE_SET_JAVA8 = Collections.unmodifiableSet(set);
    }


    /**
     * java9+
     */
    // 使用of方法初始化不可变集合
    private static final List<Integer> UNMODIFIABLE_LIST_JAVA9 = List.of(1, 2, 3);
    // 使用of方法初始化不可变集合 ⭐⭐⭐⭐⭐
    private static final Map<String, Integer> UNMODIFIABLE_MAP_JAVA9 = Map.of("a", 1, "b", 2);
    // Map.of() 最多支持 10 个键值对，超过时使用 Map.ofEntries() 初始化不可变集合 ⭐⭐⭐⭐⭐
    private static final Map<String, Integer> UNMODIFIABLE_LARGE_MAP_JAVA9 = Map.ofEntries(
            Map.entry("a", 1),
            Map.entry("b", 2),
            Map.entry("c", 3)
    );
    
    // 使用 Collections.unmodifiableList() 初始化不可变集合 ⭐⭐⭐⭐⭐
    private static final List<Integer> UNMODIFIABLE_LIST_WITH_NULL_JAVA9 = Collections.unmodifiableList(
            new ArrayList<>(List.of(1, null, 3))
    );

    public static void main(String[] args) {
        // Java8-
        System.out.println(UNMODIFIABLE_LIST_JAVA8);
        System.out.println(UNMODIFIABLE_LIST_WITH_NULL_JAVA8);
        System.out.println(MUTABLE_LIST);

        // java9+
        System.out.println(UNMODIFIABLE_LIST_JAVA9);
        System.out.println(UNMODIFIABLE_MAP_JAVA9);
        System.out.println(UNMODIFIABLE_LARGE_MAP_JAVA9);
        System.out.println(UNMODIFIABLE_LIST_WITH_NULL_JAVA9);
    }

}
