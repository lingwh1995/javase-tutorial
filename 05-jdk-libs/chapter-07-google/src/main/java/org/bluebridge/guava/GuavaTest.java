package org.bluebridge.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Guava 渐进式教学指南
 *
 * @author lingwh
 * @date 2026/6/12 13:47
 */
public class GuavaTest {

    public static void main(String[] args) {
        chapter1_basicUtilities();
        chapter2_strings();
        chapter3_immutableCollections();
        chapter4_newCollections();
        chapter5_cacheAndEventBus();
    }

    /**
     * chapter 1: 基础工具
     * 核心思想：快速失败，比起 JDK 原生的 if-else 抛异常，代码更简洁
     */
    private static void chapter1_basicUtilities() {
        // 1. 检查参数是否为空 (替代 if (name == null) throw new...)
        String name = "Guava";
        Preconditions.checkNotNull(name, "Name must not be null");

        // 2. 检查条件是否为 true (替代 if (age < 0) throw new...)
        int age = 25;
        Preconditions.checkArgument(age >= 0, "Age must be non-negative: %s", age);

        // 3. 检查状态 (常用于对象初始化后的状态校验)
        boolean isInitialized = true;
        Preconditions.checkState(isInitialized, "Service is not initialized");

        // 4. 检查下标 (替代手动检查 IndexOutOfBoundsException)
        List<String> list = ImmutableList.of("A", "B");
        Preconditions.checkElementIndex(1, list.size(), "Index out of bounds for list");

        System.out.println("Level 1: 所有前置条件校验通过！");
    }

    /**
     * chapter2: 字符串处理
     * 核心思想：解决 JDK 原生 String.split 的坑（如丢弃尾部空字符串），提供流式 API
     */
    private static void chapter2_strings() {
        // 1. Joiner (连接器) - 自动处理 null 值
        List<String> parts = Lists.newArrayList("Java", null, "Guava", "Kotlin");
        // 跳过 null
        String result1 = Joiner.on(", ").skipNulls().join(parts);
        System.out.println("Joiner (skipNulls): " + result1);
        // 替换 null 为默认值
        String result2 = Joiner.on(", ").useForNull("N/A").join(parts);
        System.out.println("Joiner (useForNull): " + result2);

        // 2. Splitter (分割器) - 更强大更严格的分割
        String csv = ",a,,b,c,";
        // JDK 原生 split 会静默丢弃尾部的空字符串，且结果带有空格
        // Guava Splitter 默认不丢弃空字符串，行为更可预测
        List<String> jdkSplit = List.of(csv.split(","));
        List<String> guavaSplit = Splitter.on(',')
                .trimResults()          // 去除前后空格
                .omitEmptyStrings()     // 忽略空字符串
                .splitToList(csv);
        System.out.println("JDK Split: " + jdkSplit);
        System.out.println("Guava Split: " + guavaSplit);

        // 3. CharMatcher (字符匹配器) - 替代复杂的正则表达式
        String messy = "  Hell0 W0rld! 123 ";
        // 只保留字母和数字
        String clean = CharMatcher.inRange('a', 'z')
                .or(CharMatcher.inRange('A', 'Z'))
                .or(CharMatcher.digit())
                .retainFrom(messy);
        System.out.println("CharMatcher retain letters/digits: " + clean);
    }

    /**
     * chapter3: 不可变集合
     * 核心思想：不可变对象是安全的、自由的、高效的。Guava 是 Java 界推广不可变对象的先驱
     */
    private static void chapter3_immutableCollections() {
        // 1. of() 方法：快速创建包含已知元素的不可变集合
        ImmutableList<String> immutableList = ImmutableList.of("A", "B", "C");
        // immutableList.add("D"); // 会抛出 UnsupportedOperationException

        // 2. builder() 方法：渐进式构建不可变集合
        ImmutableSet<String> colorSet = ImmutableSet.<String>builder()
                .add("Red", "Green")
                .add("Blue")
                .build();
        System.out.println("ImmutableSet: " + colorSet);

        // 3. copyOf() 方法：从现有集合拷贝（防御性编程）
        List<String> mutableList = Lists.newArrayList("X", "Y", "Z");
        ImmutableList<String> defensiveCopy = ImmutableList.copyOf(mutableList);
        mutableList.add("W"); // 修改原集合
        System.out.println("Original mutable list: " + mutableList);
        System.out.println("Defensive immutable copy: " + defensiveCopy); // 不受影响
    }

    /**
     * chapter4: 新集合类型
     * 核心思想：解决 JDK 中繁琐的 Map<K, List<V>> 等嵌套结构，减少模板代码
     */
    private static void chapter4_newCollections() {
        // 1. Multiset (无序可重复集合) - 统计词频的神器
        // 不再需要 Map<String, Integer> 并手动处理加减逻辑
        Multiset<String> wordCounts = HashMultiset.create();
        wordCounts.add("Apple", 3);
        wordCounts.add("Banana", 2);
        wordCounts.add("Apple"); // 再加 1 个 Apple
        System.out.println("Multiset count of Apple: " + wordCounts.count("Apple")); // 4

        // 2. Multimap (一键多值映射) - 不再需要 Map<K, Collection<V>>
        // 比如一个学生选课系统
        Multimap<String, String> studentCourses = ArrayListMultimap.create();
        studentCourses.put("Alice", "Math");
        studentCourses.put("Alice", "Science");
        studentCourses.put("Bob", "History");
        System.out.println("Alice's courses: " + studentCourses.get("Alice")); // [Math, Science]

        // 3. BiMap (双向映射) - 键值对反转
        // 比如英文-中文词典，需要通过中文查英文
        BiMap<String, String> dictionary = HashBiMap.create();
        dictionary.put("Apple", "苹果");
        dictionary.put("Banana", "香蕉");
        // 通过 value 获取 key
        System.out.println("反转查找 '苹果': " + dictionary.inverse().get("苹果")); // Apple
        // dictionary.put("Orange", "苹果"); // 会抛异常，因为 value 重复了！
        dictionary.forcePut("Orange", "苹果"); // 强制覆盖原来的 Apple
        System.out.println("强制覆盖后的词典: " + dictionary);

        // 4. Table (二维表) - 不再需要 Map<RowKey, Map<ColKey, Value>>
        // 比如学生-科目-成绩
        Table<String, String, Integer> grades = HashBasedTable.create();
        grades.put("Alice", "Math", 95);
        grades.put("Alice", "Science", 88);
        grades.put("Bob", "Math", 82);
        System.out.println("Alice's Math grade: " + grades.get("Alice", "Math")); // 95
    }

    /**
     * chapter5: 缓存与事件总线
     * 核心思想：提供开箱即用的本地缓存和进程内发布-订阅模型
     */
    private static void chapter5_cacheAndEventBus() {
        // 1. LoadingCache (本地缓存)
        // 适用于读多写少，且计算/获取数据成本高的场景
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)                     // 最大缓存条目数
                .expireAfterWrite(10, TimeUnit.MINUTES) // 写入后10分钟过期
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        // 缓存未命中时的加载逻辑（如查数据库）
                        System.out.println(" -> [DB Query] Loading data for key: " + key);
                        return "Value_of_" + key;
                    }
                });

        try {
            System.out.println("First query (Cache Miss): " + cache.get("Key1"));
            System.out.println("Second query (Cache Hit): " + cache.get("Key1")); // 不会触发 DB Query
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. EventBus (事件总线)
        // 简单的观察者模式实现，解耦事件发布者和消费者
        EventBus eventBus = new EventBus();
        // 注册订阅者
        eventBus.register(new Object() {
            @Subscribe
            public void handleMessage(String message) {
                System.out.println("Subscriber received message: " + message);
            }
        });
        // 发布事件
        eventBus.post("Hello Guava EventBus!");
    }
}
