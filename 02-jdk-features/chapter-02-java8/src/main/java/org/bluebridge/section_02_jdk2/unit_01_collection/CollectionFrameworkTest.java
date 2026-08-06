package org.bluebridge.section_02_jdk2.unit_01_collection;

import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;

/**
 * JDK 1.2 集合框架(Collections Framework)特性测试
 *
 * JDK 1.2 引入了完整的集合框架, 统一了集合操作的标准接口和实现:
 * 1. Collection 接口: 所有单列集合的根接口, 包括 List 和 Set
 * 2. List 接口: 有序、可重复的集合, 实现类有 ArrayList、LinkedList、Vector
 * 3. Set 接口: 无序、不可重复的集合, 实现类有 HashSet、TreeSet、LinkedHashSet
 * 4. Map 接口: 键值对存储, 实现类有 HashMap、TreeMap、LinkedHashMap、Hashtable
 * 5. Collections 工具类: 提供排序、查找、同步等静态方法
 * 6. Iterator 迭代器: 统一遍历集合的方式
 * 7. Comparable 和 Comparator: 提供排序比较策略
 *
 * @author lingwh
 * @date 2026/08/05 19:02
 */
public class CollectionFrameworkTest {

    // ==================== List 接口测试 ====================

    /**
     * 测试 ArrayList: 基于数组实现的 List, 随机访问快, 插入删除慢
     */
    @Test
    public void testArrayList() {
        // 创建 ArrayList 并添加元素
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Python");
        arrayList.add("C++");
        arrayList.add("JavaScript");
        System.out.println("ArrayList 初始内容: " + arrayList);

        // 按索引插入元素
        arrayList.add(1, "Go");
        System.out.println("索引 1 插入 Go 后: " + arrayList);

        // 按索引获取元素(随机访问, 时间复杂度 O(1))
        String element = arrayList.get(2);
        System.out.println("索引 2 的元素: " + element);

        // 按索引移除元素
        arrayList.remove(3);
        System.out.println("移除索引 3 后: " + arrayList);

        // 判断是否包含某个元素
        boolean contains = arrayList.contains("Java");
        System.out.println("是否包含 Java: " + contains);

        // 获取元素个数
        System.out.println("ArrayList 大小: " + arrayList.size());
    }

    /**
     * 测试 LinkedList: 基于双向链表实现的 List, 插入删除快, 随机访问慢
     */
    @Test
    public void testLinkedList() {
        // 创建 LinkedList 并添加元素
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        System.out.println("LinkedList 初始内容: " + linkedList);

        // 在头部添加元素
        linkedList.addFirst("First");
        System.out.println("头部添加后: " + linkedList);

        // 在尾部添加元素
        linkedList.addLast("Last");
        System.out.println("尾部添加后: " + linkedList);

        // 获取头部元素(不移除)
        String first = linkedList.getFirst();
        System.out.println("头部元素: " + first);

        // 获取尾部元素(不移除)
        String last = linkedList.getLast();
        System.out.println("尾部元素: " + last);

        // 移除头部元素
        linkedList.removeFirst();
        System.out.println("移除头部后: " + linkedList);

        // 移除尾部元素
        linkedList.removeLast();
        System.out.println("移除尾部后: " + linkedList);

        // LinkedList 也可作为队列使用
        linkedList.offer("D"); // 入队
        System.out.println("队列 offer D 后: " + linkedList);
        String polled = linkedList.poll(); // 出队
        System.out.println("出队元素: " + polled + ", 剩余: " + linkedList);
    }

    // ==================== Set 接口测试 ====================

    /**
     * 测试 HashSet: 基于 HashMap 实现的 Set, 无序、不重复
     */
    @Test
    public void testHashSet() {
        // 创建 HashSet 并添加元素
        Set<String> hashSet = new HashSet<>();
        hashSet.add("香蕉");
        hashSet.add("苹果");
        hashSet.add("橘子");
        hashSet.add("苹果"); // 重复元素, 不会添加成功
        System.out.println("HashSet 内容(无序、无重复): " + hashSet);

        // 添加 null 元素(HashSet 允许 null)
        hashSet.add(null);
        System.out.println("添加 null 后: " + hashSet);

        // 移除元素
        hashSet.remove("橘子");
        System.out.println("移除橘子后: " + hashSet);

        // 判断是否包含
        System.out.println("是否包含苹果: " + hashSet.contains("苹果"));

        // 获取大小
        System.out.println("HashSet 大小: " + hashSet.size());
    }

    /**
     * 测试 TreeSet: 基于 TreeMap 实现的 Set, 元素自动排序, 不重复
     */
    @Test
    public void testTreeSet() {
        // 创建 TreeSet 并添加元素(默认自然排序)
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(5);
        treeSet.add(1);
        treeSet.add(8);
        treeSet.add(3);
        System.out.println("TreeSet 内容(自动升序): " + treeSet);

        // 第一个和最后一个元素
        TreeSet<Integer> ts = (TreeSet<Integer>) treeSet;
        System.out.println("第一个元素: " + ts.first());
        System.out.println("最后一个元素: " + ts.last());

        // 获取子集(不包含头尾)
        SortedSet<Integer> subSet = ts.subSet(3, 8);
        System.out.println("子集 [3, 8): " + subSet);

        // 小于等于某个值的元素集合
        SortedSet<Integer> headSet = ts.headSet(5);
        System.out.println("小于 5 的元素: " + headSet);

        // 大于等于某个值的元素集合
        SortedSet<Integer> tailSet = ts.tailSet(5);
        System.out.println("大于等于 5 的元素: " + tailSet);
    }

    // ==================== Map 接口测试 ====================

    /**
     * 测试 HashMap: 基于哈希表实现的 Map, 键值对存储, 键无序
     */
    @Test
    public void testHashMap() {
        // 创建 HashMap 并添加键值对
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("张三", 85);
        hashMap.put("李四", 92);
        hashMap.put("王五", 78);
        hashMap.put("赵六", 95);
        System.out.println("HashMap 内容: " + hashMap);

        // 获取值
        Integer score = hashMap.get("李四");
        System.out.println("李四的成绩: " + score);

        // 判断是否包含键
        System.out.println("是否包含张三: " + hashMap.containsKey("张三"));

        // 判断是否包含值
        System.out.println("是否包含 100 分: " + hashMap.containsValue(100));

        // 获取所有键
        Set<String> keys = hashMap.keySet();
        System.out.println("所有键: " + keys);

        // 获取所有值
        Collection<Integer> values = hashMap.values();
        System.out.println("所有值: " + values);

        // 移除键值对
        hashMap.remove("王五");
        System.out.println("移除王五后: " + hashMap);

        // 获取大小
        System.out.println("HashMap 大小: " + hashMap.size());
    }

    /**
     * 测试 TreeMap: 基于红黑树实现的 Map, 键自动排序
     */
    @Test
    public void testTreeMap() {
        // 创建 TreeMap 并添加键值对(键按自然排序)
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("D-Dog", "狗");
        treeMap.put("A-Apple", "苹果");
        treeMap.put("C-Cat", "猫");
        treeMap.put("B-Bird", "鸟");
        System.out.println("TreeMap 内容(键自动排序): " + treeMap);

        // 第一个和最后一个键值对
        TreeMap<String, String> tm = (TreeMap<String, String>) treeMap;
        System.out.println("第一个键值对: " + tm.firstEntry());
        System.out.println("最后一个键值对: " + tm.lastEntry());

        // 按范围获取子 Map
        SortedMap<String, String> subMap = tm.subMap("B-Bird", "D-Dog");
        System.out.println("子 Map [B, D): " + subMap);
    }

    // ==================== Collections 工具类测试 ====================

    /**
     * 测试 Collections.sort(): 对 List 进行排序
     */
    @Test
    public void testCollectionsSort() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);
        System.out.println("排序前: " + numbers);

        // 自然排序(升序)
        Collections.sort(numbers);
        System.out.println("升序排序后: " + numbers);

        // 使用 Comparator 降序排序
        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println("降序排序后: " + numbers);
    }

    /**
     * 测试 Collections.reverse(): 反转 List 的顺序
     */
    @Test
    public void testCollectionsReverse() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        System.out.println("反转前: " + list);

        Collections.reverse(list);
        System.out.println("反转后: " + list);
    }

    /**
     * 测试 Collections.binarySearch(): 二分查找(要求 List 已排序)
     */
    @Test
    public void testCollectionsBinarySearch() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        // 二分查找前必须排序
        Collections.sort(list);

        int index = Collections.binarySearch(list, 30);
        System.out.println("元素 30 的索引位置: " + index);

        // 查找不存在的元素, 返回负数
        int notFound = Collections.binarySearch(list, 35);
        System.out.println("元素 35 的查找结果(负数表示未找到): " + notFound);
    }

    /**
     * 测试 Collections.max() 和 Collections.min(): 获取最大值和最小值
     */
    @Test
    public void testCollectionsMaxAndMin() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(45);
        numbers.add(12);
        numbers.add(78);
        numbers.add(33);
        numbers.add(91);

        // 获取最大值(自然排序)
        Integer max = Collections.max(numbers);
        System.out.println("最大值: " + max);

        // 获取最小值(自然排序)
        Integer min = Collections.min(numbers);
        System.out.println("最小值: " + min);

        // 使用 Comparator 自定义比较规则获取最大值
        List<String> words = new ArrayList<>();
        words.add("Java");
        words.add("Python");
        words.add("C");
        words.add("JavaScript");
        // 按字符串长度获取最大值
        String longest = Collections.max(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println("最长的单词: " + longest);
    }

    // ==================== Iterator 迭代器测试 ====================

    /**
     * 测试使用 Iterator 遍历 List 集合
     */
    @Test
    public void testIteratorWithList() {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("C++");
        list.add("Go");

        // 使用 Iterator 遍历
        System.out.println("使用 Iterator 遍历 List:");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("  " + element);
        }
    }

    /**
     * 测试使用 Iterator 遍历 Set 集合
     */
    @Test
    public void testIteratorWithSet() {
        Set<String> set = new HashSet<>();
        set.add("红色");
        set.add("绿色");
        set.add("蓝色");

        // 使用 Iterator 遍历
        System.out.println("使用 Iterator 遍历 Set:");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("  " + element);
        }
    }

    /**
     * 测试使用 Iterator 遍历 Map 集合(通过 entrySet)
     */
    @Test
    public void testIteratorWithMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Java", 1995);
        map.put("Python", 1991);
        map.put("Go", 2009);

        // 使用 Iterator 遍历 Map 的键值对
        System.out.println("使用 Iterator 遍历 Map:");
        Iterator<Entry<String, Integer>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Entry<String, Integer> entry = entryIterator.next();
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        // 使用 Iterator 遍历 Map 的键
        System.out.println("使用 Iterator 遍历 Map 的键:");
        Iterator<String> keyIterator = map.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            System.out.println("  " + key + " -> " + map.get(key));
        }
    }

    /**
     * 测试 Iterator 在遍历过程中移除元素
     */
    @Test
    public void testIteratorRemove() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println("移除前: " + list);

        // 使用 Iterator 遍历并移除偶数元素
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            if (num % 2 == 0) {
                iterator.remove(); // 安全的移除方式
            }
        }
        System.out.println("移除偶数后: " + list);
        // 注意: 在 for-each 循环中直接调用 list.remove() 会抛出 ConcurrentModificationException
    }

    // ==================== Comparable 和 Comparator 测试 ====================

    /**
     * 测试 Comparable 自然排序: 让元素自身实现 Comparable 接口
     */
    @Test
    public void testComparableSort() {
        // Person 类实现了 Comparable 接口, 按年龄升序排序
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("张三", 30));
        persons.add(new Person("李四", 25));
        persons.add(new Person("王五", 35));
        persons.add(new Person("赵六", 28));

        System.out.println("排序前: " + persons);
        Collections.sort(persons);
        System.out.println("按年龄排序后: " + persons);
    }

    /**
     * 测试 Comparator 定制排序: 灵活定义多种排序规则
     */
    @Test
    public void testComparatorSort() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("张三", 30));
        persons.add(new Person("李四", 25));
        persons.add(new Person("王五", 35));
        persons.add(new Person("赵六", 28));

        // 按姓名升序排序
        System.out.println("按姓名排序前: " + persons);
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        System.out.println("按姓名排序后: " + persons);

        // 按年龄降序排序
        System.out.println("按年龄降序排序前: " + persons);
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p2.getAge() - p1.getAge();
            }
        });
        System.out.println("按年龄降序排序后: " + persons);
    }

    /**
     * 测试使用 Collections 的其他常用方法
     */
    @Test
    public void testCollectionsOtherMethods() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // shuffle: 随机打乱
        Collections.shuffle(list);
        System.out.println("shuffle 打乱后: " + list);

        // fill: 填充所有元素
        Collections.fill(list, 0);
        System.out.println("fill 填充 0 后: " + list);

        // copy: 复制
        List<Integer> dest = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
        List<Integer> src = Arrays.asList(10, 20, 30, 40, 50);
        Collections.copy(dest, src);
        System.out.println("copy 后 dest: " + dest);

        // frequency: 统计元素出现次数
        List<String> words = new ArrayList<>();
        words.add("Java");
        words.add("Python");
        words.add("Java");
        words.add("Go");
        words.add("Java");
        int freq = Collections.frequency(words, "Java");
        System.out.println("Java 出现的次数: " + freq);

        // replaceAll: 替换所有指定元素
        Collections.replaceAll(words, "Java", "JAVA");
        System.out.println("replaceAll 后: " + words);

        // swap: 交换两个位置的元素
        List<String> swapList = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Collections.swap(swapList, 0, 3);
        System.out.println("swap 索引 0 和 3 后: " + swapList);

        // 不可变集合
        List<String> unmodifiableList = Collections.unmodifiableList(new ArrayList<>(Arrays.asList("X", "Y", "Z")));
        System.out.println("不可变集合: " + unmodifiableList);
        // unmodifiableList.add("W"); // 会抛出 UnsupportedOperationException

        // 同步包装: 将非线程安全的集合包装为线程安全的
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        System.out.println("同步包装的 List: " + synchronizedList.getClass().getName());
    }

    // ==================== 内部辅助类 ====================

    /**
     * 测试用的 Person 类, 实现 Comparable 接口, 按年龄自然排序
     */
    static class Person implements Comparable<Person> {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public int compareTo(Person other) {
            // 按年龄升序排序
            return this.age - other.age;
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
}